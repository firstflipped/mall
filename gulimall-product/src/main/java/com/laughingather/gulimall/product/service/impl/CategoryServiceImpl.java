package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.constant.ProductConstants;
import com.laughingather.gulimall.common.util.JsonUtil;
import com.laughingather.gulimall.product.dao.CategoryDao;
import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.Category2VO;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;
import com.laughingather.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * @author WangJie
 */
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Resource
    private CategoryDao categoryDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private RedissonClient redissonClient;

    @Override
    public List<CategoryTreeVO> listWithTree() {
        // 查询所有分类
        List<CategoryTreeVO> categoryList = categoryDao.selectListWithTree();

        // 利用流对数据进行树形组装、排序
        return categoryList.stream()
                .filter(category -> ProductConstants.CATEGORY_ROOT_ID.equals(category.getParentId()))
                .peek(category -> category.setChildren(getChildless(category, categoryList)))
                .sorted(Comparator.comparingInt(CategoryTreeVO::getSort))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategoryByIds(List<Long> categoryIds) {
        // TODO:遍历需要被删除的id，确定在其他位置是否存在引用

        categoryDao.deleteBatchIds(categoryIds);
    }

    @Override
    public Long[] getCategoryPath(Long categoryId) {
        List<Long> categoryPath = new ArrayList<>();

        // 查询上级分类id，组装完整节点路径
        categoryPath = findParentById(categoryId, categoryPath);
        Collections.reverse(categoryPath);

        return categoryPath.toArray(new Long[0]);
    }

    /**
     * 查询所有一级分类
     * <p>
     * 注解@Cacheable：代表当前方法的结果需要缓存。如果缓存中有，则方法直接就不需要调用，如果缓存中没有，会调用方法，最后将方法的结果缓存
     * value：每一个需要缓存的数据都需要我们来指定放到缓存的那个区域中
     * key：指定键的名称
     * time-to-live：在配置文件中配置过期时间
     * <p>
     * SpringCache的局限性在于并发时的读写锁的实现是应用锁，一个应用一把锁
     *
     * @return 分类列表
     */
    @Override
    @Cacheable(value = "category", key = "'level1Category'", sync = true)
    public List<CategoryEntity> listLevel1Category() {
        log.info("进行了查询操作，没有触发缓存");

        return categoryDao.selectList(new LambdaQueryWrapper<CategoryEntity>()
                .eq(CategoryEntity::getCategoryLevel, 1));
    }


    /**
     * TODO：产生堆外内存溢出：OutOfDirectMemoryError
     * springboot2.0以后使用lettuce作为操作redis的客户端。它使用netty进行网络通信
     * lettuce的bug导致netty堆外内存溢出
     * <p>
     * 解决方案：1、升级lettuce客户端（现版本已解决）    2、使用jedis客户端
     * <p>
     * 如果对锁的要求比较高就使用手写逻辑实现
     *
     * @return 分类字典数据
     */
    @Override
    public Map<String, List<Category2VO>> getCategoryMap() {
        /*
         * redis自实现分布式锁
         * 1、空结果缓存：解决缓存穿透问题（缓存穿透主要是查询一个一定不存在的数据造成的）
         * 2、设置过期时间加随机值：解决缓存雪崩问题（缓存雪崩主要是在同一时间段大量缓存时间过期造成的）
         * 3、加锁：解决缓存击穿问题（缓存击穿主要是有一个热点数据再大级别访问请求之前缓存刚好失效造成的）
         */
        String categoryJson = redisTemplate.opsForValue().get(ProductConstants.CATEGORY);
        Map<String, List<Category2VO>> categoryMap = JsonUtil.string2Obj(categoryJson, Map.class);
        if (MapUtils.isEmpty(categoryMap)) {
            return getCategoryJsonFromDbWithRedissonLock();
        }
        return categoryMap;
    }


    /**
     * redisson分布式锁
     * <p>
     * 建议使用redisson
     * 成熟的redis分布式锁解决方案
     * 1、支持阻塞
     * 2、锁的自动续期，防止锁自动过期（加锁的时候设置了默认过期时间，如果业务执行时间过长会自动续期，会更新锁的有效时间，不需要担心锁自动过期）
     * 3、锁的自动释放，防止死锁（如果没有业务执行，就不会给锁的有效时间进行续期，就算没有手动释放锁，也会在过期时间之后自动删除锁，防止死锁问题）
     * <p>
     * 锁粒度：锁的粒度最好是越细越好
     * <p>
     * 数据一致性：缓存里面的数据如何和数据库保持一致
     * 1）双写模式：修改数据库数据的时候，同步修改缓存中的数据
     * 2）失效模式：修改数据库数据的时候，直接把缓存中的数据清空，等待下次主动查询设置缓存
     * 以上两种解决方案都会导致缓存的不一致问题
     * <p>
     * 我们能放入缓存的数据本就不应该是实时性、一致性要求超高的。所以缓存数据的时候加上过期时间就能满足大部分需求
     * 如果遇到实时性、一致性要求高的数据，就应该查数据库，即使慢点
     * <p>
     * 最终解决方案
     * 1、缓存的所有数据都加上过期时间，数据过期下一次查询触发主动更新
     * 2、读写数据的时候加上分布式的读写锁
     *
     * @return 分类字典数据
     */
    private Map<String, List<Category2VO>> getCategoryJsonFromDbWithRedissonLock() {
        RLock categoryLock = redissonClient.getLock(ProductConstants.CATEGORY_LOCK);
        categoryLock.lock(30, TimeUnit.SECONDS);
        log.info("获取分布式锁成功");
        Map<String, List<Category2VO>> dataFromDb;
        try {
            // 查询数据库操作
            dataFromDb = getCategoryMapFromDb();
        } finally {
            categoryLock.unlock();
        }
        return dataFromDb;
    }


    @Override
    public Map<String, List<Category2VO>> getCategoryMapFromDb() {
        String categoryJson = redisTemplate.opsForValue().get(ProductConstants.CATEGORY);
        Map<String, List<Category2VO>> categoryMap = JsonUtil.string2Obj(categoryJson, Map.class);
        if (MapUtils.isNotEmpty(categoryMap)) {
            return categoryMap;
        }

        log.info("查询没有命中缓存，触发了数据库操作");
        // 先查询所有分类信息
        List<CategoryEntity> categories = categoryDao.selectList(null);

        // 1、获取所有一级分类
        List<CategoryEntity> category1List = listCategoryByParentId(categories, ProductConstants.CATEGORY_ROOT_ID);

        // 2、把一级分类id当键， 二级分类及子类当值
        categoryMap = category1List.stream().collect(Collectors.toMap(
                k -> k.getCategoryId().toString(),
                v -> {
                    List<CategoryEntity> category2List = listCategoryByParentId(categories, v.getCategoryId());
                    List<Category2VO> category2VOList = Collections.emptyList();
                    if (CollectionUtils.isNotEmpty(category2List)) {
                        category2VOList = category2List.stream().map(category2 -> {
                            Category2VO category2VO = Category2VO.builder().id(category2.getCategoryId().toString())
                                    .name(category2.getCategoryName())
                                    .category1Id(v.getCategoryId().toString()).build();

                            List<CategoryEntity> category3List = listCategoryByParentId(categories, category2.getCategoryId());
                            if (CollectionUtils.isNotEmpty(category3List)) {
                                List<Category2VO.Category3VO> category3VOList = category3List.stream().map(category3 ->
                                        Category2VO.Category3VO.builder().id(category3.getCategoryId().toString())
                                                .name(category3.getCategoryName())
                                                .category2Id(category2.getCategoryId().toString())
                                                .build()
                                ).collect(Collectors.toList());
                                category2VO.setCatalog3List(category3VOList);
                            }
                            return category2VO;
                        }).collect(Collectors.toList());
                    }
                    return category2VOList;
                }
        ));

        redisTemplate.opsForValue().set(ProductConstants.CATEGORY, JsonUtil.obj2String(categoryMap));
        return categoryMap;
    }


    /**
     * 查询父节点id
     *
     * @param categoryId   分类id
     * @param categoryPath 分类完整节点路径
     * @return 分类完整节点路径
     */
    private List<Long> findParentById(Long categoryId, List<Long> categoryPath) {
        if (categoryId == null) {
            return Collections.emptyList();
        }

        // 收集当前节点id
        categoryPath.add(categoryId);

        CategoryEntity category = categoryDao.selectById(categoryId);
        Long parentId = category.getParentId();

        // 如果不是顶级分类就继续向上查找
        if (!ProductConstants.CATEGORY_ROOT_ID.equals(parentId)) {
            findParentById(parentId, categoryPath);
        }

        return categoryPath;
    }

    /**
     * 递归查找所有菜单的子菜单
     *
     * @param root 分类父节点信息
     * @param all 分类列表
     * @return 分类VO列表
     */
    private List<CategoryTreeVO> getChildless(CategoryTreeVO root, List<CategoryTreeVO> all) {
        return all.stream()
                .filter(category -> category.getParentId().equals(root.getCategoryId()))
                // 找到子菜单
                .peek(category -> category.setChildren(getChildless(category, all)))
                .sorted((c1, c2) -> (c1.getSort() == null ? 0 : c1.getSort()) - (c2.getSort() == null ? 0 : c1.getSort()))
                .collect(Collectors.toList());
    }

    /**
     * 根据父id查询其子分类
     *
     * @param parentId 分类父id
     * @return 分类列表
     */
    private List<CategoryEntity> listCategoryByParentId(Long parentId) {
        return categoryDao.selectList(new LambdaQueryWrapper<CategoryEntity>().eq(CategoryEntity::getParentId, parentId));
    }

    private List<CategoryEntity> listCategoryByParentId(List<CategoryEntity> categoryList, Long parentId) {
        return categoryList.stream().filter(item -> parentId.equals(item.getParentId())).collect(Collectors.toList());
    }


    /**
     * redis分布式锁（自己实现的）
     *
     * @return 分类字典数据
     */
    @Deprecated
    private Map<String, List<Category2VO>> getCatalogJsonFromDbWithRedisLock() {
        String uuid = UUID.randomUUID().toString();
        // 利用redis的NX特性获取唯一的锁，并且设置过期时间，防止形成死锁问题（加锁和设置过期时间必须是原子性的）
        Boolean categoryLock = redisTemplate.opsForValue().setIfAbsent("category_lock", uuid, 300, TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(categoryLock)) {
            log.info("获取分布式锁成功");
            Map<String, List<Category2VO>> dataFromDb;
            try {
                dataFromDb = getCategoryMapFromDb();
            } finally {
                String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                        "    return redis.call(\"del\",KEYS[1])\n" +
                        "else\n" +
                        "    return 0\n" +
                        "end";
                // 使用lua脚本来实现分布式锁的删除操作（获取锁的匹配值和删除锁必须是原子性的）
                redisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList("category_lock"), uuid);
            }
            return dataFromDb;
        } else {
            // 利用自旋锁来实现阻塞，可以加一个休眠时间
            log.info("获取分布式锁失败，进行重试");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getCatalogJsonFromDbWithRedisLock();
        }

    }

    /**
     * 一级一级查数据库的实现方式
     *
     * @return 分类字典数据
     */
    @Deprecated
    public Map<String, List<Category2VO>> getCatalogJsonOld() {
        // 1、获取所有一级分类
        List<CategoryEntity> categoryLevel1List = listLevel1Category();

        // 把一级分类id当键， 二级分类及子类当值
        return categoryLevel1List.stream().collect(Collectors.toMap(
                k -> k.getCategoryId().toString(),
                v -> {
                    List<CategoryEntity> category2List = listCategoryByParentId(v.getCategoryId());
                    List<Category2VO> category2VOList = Collections.emptyList();
                    if (CollectionUtils.isNotEmpty(category2List)) {
                        category2VOList = category2List.stream().map(category2 -> {
                            Category2VO category2VO = Category2VO.builder().id(category2.getCategoryId().toString())
                                    .name(category2.getCategoryName())
                                    .category1Id(v.getCategoryId().toString()).build();

                            List<CategoryEntity> category3List = listCategoryByParentId(category2.getCategoryId());
                            if (CollectionUtils.isNotEmpty(category3List)) {
                                List<Category2VO.Category3VO> category3VOList = category3List.stream().map(category3 ->
                                        Category2VO.Category3VO.builder().id(category3.getCategoryId().toString())
                                                .name(category3.getCategoryName())
                                                .category2Id(category2.getCategoryId().toString())
                                                .build()
                                ).collect(Collectors.toList());
                                category2VO.setCatalog3List(category3VOList);
                            }
                            return category2VO;
                        }).collect(Collectors.toList());
                    }
                    return category2VOList;
                }
        ));
    }

}