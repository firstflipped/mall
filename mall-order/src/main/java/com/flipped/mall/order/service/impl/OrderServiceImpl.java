package com.flipped.mall.order.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.common.constant.Constants;
import com.flipped.mall.common.constant.OrderConstants;
import com.flipped.mall.common.entity.api.ErrorCodeEnum;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.entity.api.ResultCodeEnum;
import com.flipped.mall.common.util.JsonUtil;
import com.flipped.mall.order.dao.OrderDao;
import com.flipped.mall.order.entity.OrderEntity;
import com.flipped.mall.order.entity.OrderItemEntity;
import com.flipped.mall.order.entity.dto.OrderDTO;
import com.flipped.mall.order.entity.dto.PayDTO;
import com.flipped.mall.order.entity.dto.WareSkuLockDTO;
import com.flipped.mall.order.entity.param.OrderCreateParam;
import com.flipped.mall.order.entity.param.OrderSubmitParam;
import com.flipped.mall.order.entity.query.OrderQuery;
import com.flipped.mall.order.entity.vo.OrderConfirmVO;
import com.flipped.mall.order.entity.vo.OrderSubmitVO;
import com.flipped.mall.order.feign.entity.*;
import com.flipped.mall.order.feign.service.CartFeignService;
import com.flipped.mall.order.feign.service.MemberFeignService;
import com.flipped.mall.order.feign.service.ProductFeignService;
import com.flipped.mall.order.feign.service.WareFeignService;
import com.flipped.mall.order.interceptor.LoginUserInterceptor;
import com.flipped.mall.order.service.OrderItemService;
import com.flipped.mall.order.service.OrderService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * 订单逻辑
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    private final ThreadLocal<OrderSubmitParam> orderSubmitThreadLocal = new ThreadLocal<>();

    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderItemService orderItemService;

    @Resource
    private MemberFeignService memberFeignService;
    @Resource
    private CartFeignService cartFeignService;
    @Resource
    private WareFeignService wareFeignService;
    @Resource
    private ProductFeignService productFeignService;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;


    @Override
    public MyPage<OrderEntity> listOrdersWithPage(OrderQuery orderQuery) {

        IPage<OrderEntity> page = new Page<>(orderQuery.getPn(), orderQuery.getPs());

        // 拼装查询条件
        LambdaQueryWrapper<OrderEntity> queryWrapper = Wrappers.lambdaQuery(OrderEntity.class);
        if (StringUtils.isNotBlank(orderQuery.getOrderSn())) {
            queryWrapper.eq(OrderEntity::getOrderSn, orderQuery.getOrderSn());
        }
        if (StringUtils.isNotBlank(orderQuery.getMemberUsername())) {
            queryWrapper.like(OrderEntity::getMemberUsername, orderQuery.getMemberUsername());
        }
        if (orderQuery.getStatus() != null) {
            queryWrapper.eq(OrderEntity::getStatus, orderQuery.getStatus());
        }

        IPage<OrderEntity> orderPage = orderDao.selectPage(page, queryWrapper);
        return MyPage.restPage(orderPage);
    }


    @Override
    public OrderConfirmVO confirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVO orderConfirmVO = new OrderConfirmVO();
        // 从拦截类中获取用户信息
        MemberDTO member = LoginUserInterceptor.loginUser.get();

        // 拿到主线程的请求信息，将请求信息设置到副线程里面（每一个线程都共享请求信息）
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        CompletableFuture<Void> addressCompletableFuture = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            // 1、远程调用会员服务获取收货地址信息
            MyResult<List<MemberReceiveAddressDTO>> memberReceiveAddressResult = memberFeignService.listMemberReceiveAddress(member.getId());
            if (memberReceiveAddressResult.getSuccess()) {
                List<MemberReceiveAddressDTO> addresses = memberReceiveAddressResult.getData();
                orderConfirmVO.setAddresses(addresses);
            }
        }, threadPoolExecutor);

        CompletableFuture<Void> itemsCompletableFuture = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            // 2、远程调用购物车服务获取购物车项信息
            MyResult<List<OrderItemDTO>> currentUserCartItemsResult = cartFeignService.getCurrentUserCartItems();
            if (currentUserCartItemsResult.getSuccess()) {
                List<OrderItemDTO> items = currentUserCartItemsResult.getData();
                orderConfirmVO.setItems(items);
            }
        }, threadPoolExecutor).thenRunAsync(() -> {
            // 3、远程调用库存服务获取商品库存信息
            List<OrderItemDTO> items = orderConfirmVO.getItems();
            if (CollectionUtils.isNotEmpty(items)) {
                List<Long> skuIds = items.stream().map(OrderItemDTO::getSkuId).collect(Collectors.toList());
                MyResult<List<SkuHashStockDTO>> skusHasStockResult = wareFeignService.getSkusHasStock(skuIds);
                if (skusHasStockResult.getSuccess()) {
                    for (OrderItemDTO item : items) {
                        for (SkuHashStockDTO skuHashStockDTO : skusHasStockResult.getData()) {
                            if (item.getSkuId().equals(skuHashStockDTO.getSkuId())) {
                                item.setHasStock(skuHashStockDTO.getHasStock());
                            }
                        }
                    }
                }
            }
        }, threadPoolExecutor);

        // 4、查询会员优惠券信息，主要是会员积分
        Integer integration = member.getIntegration();
        orderConfirmVO.setIntegration(integration);

        // 5、防重令牌
        String uuid = IdUtil.simpleUUID();
        orderConfirmVO.setOrderToken(uuid);
        redisTemplate.opsForValue().set(OrderConstants.USER_ORDER_TOKEN_PREFIX + member.getId(), uuid, 30, TimeUnit.MINUTES);

        // 等待所有异步任务完成
        CompletableFuture.allOf(addressCompletableFuture, itemsCompletableFuture).get();

        return orderConfirmVO;
    }


    /**
     * 订单提交
     *
     * @param orderSubmitParam
     * @return
     * @@Transactional：本地事务，在分布式事务下只能控制自己的回滚，不能控制远程调用的回滚
     */
    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public OrderSubmitVO submitOrder(OrderSubmitParam orderSubmitParam) {
        orderSubmitThreadLocal.set(orderSubmitParam);

        // 获取会员信息
        MemberDTO member = LoginUserInterceptor.loginUser.get();

        OrderSubmitVO orderSubmitVO = new OrderSubmitVO();

        // 1、验证令牌（令牌的对比和删除必须保证原子性）
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String orderToken = orderSubmitParam.getOrderToken();
        Long result = (Long) redisTemplate.execute(RedisScript.of(script, Long.class), Collections.singletonList(OrderConstants.USER_ORDER_TOKEN_PREFIX + member.getId()), orderToken);
        // 令牌验证失败直接返回
        if (Objects.equals(result, 0L)) {
            orderSubmitVO.setCode(ErrorCodeEnum.TOKEN_VERIFICATION_EXCEPTION.getCode());
            orderSubmitVO.setMessage(ErrorCodeEnum.TOKEN_VERIFICATION_EXCEPTION.getMessage());
            return orderSubmitVO;
        }

        // 2、下单
        OrderCreateParam orderCreateParam = createOrder();

        // 3、验价
        BigDecimal payAmount = orderCreateParam.getOrder().getPayAmount();
        BigDecimal payPrice = orderSubmitParam.getPayPrice();
        if (Math.abs(payAmount.subtract(payPrice).doubleValue()) > OrderConstants.PRICE_DIFFERENCES) {
            // 前端传入价格和后台计算价格对比，差价大于0.01直接返回错误
            orderSubmitVO.setCode(ErrorCodeEnum.PRICE_VERIFICATION_EXCEPTION.getCode());
            orderSubmitVO.setMessage(ErrorCodeEnum.PRICE_VERIFICATION_EXCEPTION.getMessage());
            return orderSubmitVO;
        }

        // 4、保存订单
        saveOrder(orderCreateParam);

        // 5、锁定库存
        WareSkuLockDTO wareSkuLockDTO = new WareSkuLockDTO();
        List<OrderItemDTO> orderItemDTOList = orderCreateParam.getOrderItems().stream().map(item -> {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setSkuId(item.getSkuId());
            orderItemDTO.setTitle(item.getSkuName());
            orderItemDTO.setCount(item.getSkuQuantity());
            return orderItemDTO;
        }).collect(Collectors.toList());
        wareSkuLockDTO.setOrderSn(orderCreateParam.getOrder().getOrderSn());
        wareSkuLockDTO.setLocks(orderItemDTOList);

        MyResult<Void> lockStockResult = wareFeignService.orderLockStock(wareSkuLockDTO);
        // 如果锁定库存失败，则返回错误
        if (!lockStockResult.getSuccess()) {
            orderSubmitVO.setCode(ErrorCodeEnum.NO_STOCK_EXCEPTION.getCode());
            orderSubmitVO.setMessage(ErrorCodeEnum.NO_STOCK_EXCEPTION.getMessage());
            return orderSubmitVO;
        }

        // 6、发送消息到消息队列
        String order = JsonUtil.obj2String(orderCreateParam.getOrder());
        rabbitTemplate.convertAndSend(OrderConstants.EXCHANGE, OrderConstants.CREATE_ROUTING_KEY, order);

        // 全部成功
        orderSubmitVO.setCode(ResultCodeEnum.SUCCESS.getCode());
        orderSubmitVO.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        orderSubmitVO.setOrder(orderCreateParam.getOrder());
        return orderSubmitVO;
    }


    @Override
    public OrderEntity getOrderByOrderSn(String orderSn) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OrderEntity::getOrderSn, orderSn);

        return orderDao.selectOne(queryWrapper);
    }


    @Override
    public void closeOrder(Long orderId) {
        OrderEntity order = orderDao.selectById(orderId);

        if (order != null && Objects.equals(OrderConstants.OrderStatusEnum.CREATE_NEW.getCode(), order.getStatus())) {
            // 更新订单状态为已取消状态
            OrderEntity updateOrder = new OrderEntity();
            updateOrder.setId(orderId);
            updateOrder.setStatus(OrderConstants.OrderStatusEnum.CANCELLED.getCode());
            orderDao.updateById(updateOrder);

            // 立即发送一个消息
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(order, orderDTO);
            rabbitTemplate.convertAndSend(OrderConstants.EXCHANGE, OrderConstants.OTHER_ROUTING_KEY, JsonUtil.obj2String(orderDTO));
        }
    }

    @Override
    public PayDTO getPayOrderInfo(String orderSn) {
        OrderEntity order = getOrderByOrderSn(orderSn);

        String payAmount = order.getPayAmount().setScale(2, BigDecimal.ROUND_UP).toString();
        return PayDTO.builder().out_trade_no(orderSn).subject("谷粒商城支付").total_amount(payAmount).body("测试").build();
    }


    /**
     * 保存订单
     *
     * @param orderCreateParam
     */
    public void saveOrder(OrderCreateParam orderCreateParam) {
        // 保存订单
        OrderEntity order = orderCreateParam.getOrder();
        order.setCreateTime(LocalDateTime.now());
        orderDao.insert(order);

        // 保存订单项
        List<OrderItemEntity> orderItems = orderCreateParam.getOrderItems();
        orderItemService.saveBatch(orderItems);
    }


    /**
     * 创建订单
     *
     * @return
     */
    private OrderCreateParam createOrder() {
        OrderSubmitParam orderSubmitParam = orderSubmitThreadLocal.get();

        OrderCreateParam orderCreateParam = new OrderCreateParam();

        // 1、组装订单信息
        OrderEntity order = buildOrder(orderSubmitParam);
        orderCreateParam.setOrder(order);

        // 2、组装订单项信息
        List<OrderItemEntity> orderItems = buildOrderItems(order.getOrderSn());
        orderCreateParam.setOrderItems(orderItems);

        // 3、计算价格、积分
        computePrice(order, orderItems);

        return orderCreateParam;
    }


    /**
     * 构建订单信息
     *
     * @param orderSubmitParam
     * @return
     */
    private OrderEntity buildOrder(OrderSubmitParam orderSubmitParam) {
        OrderEntity order = new OrderEntity();
        // 生成订单号
        String orderSn = IdWorker.getTimeId();
        order.setOrderSn(orderSn);

        // 获取session的会员信息
        MemberDTO member = LoginUserInterceptor.loginUser.get();
        // 设置会员信息
        order.setMemberId(member.getId());
        order.setMemberUsername(member.getUsername() != null ? member.getUsername() : member.getNickname());

        // 远程调用库存服务获取收货地址及运费信息
        MyResult<FareDTO> fareResult = wareFeignService.getFare(orderSubmitParam.getAddressId());
        if (fareResult.getSuccess()) {
            FareDTO fareDTO = fareResult.getData();
            // 设置运费信息
            order.setFreightAmount(fareDTO.getFare());
            // 设置收货人信息
            order.setReceiverProvince(fareDTO.getAddress().getProvince());
            order.setReceiverCity(fareDTO.getAddress().getCity());
            order.setReceiverRegion(fareDTO.getAddress().getRegion());
            order.setReceiverDetailAddress(fareDTO.getAddress().getDetailAddress());
            order.setReceiverName(fareDTO.getAddress().getName());
            order.setReceiverPhone(fareDTO.getAddress().getPhone());
            order.setReceiverPostCode(fareDTO.getAddress().getPostCode());
        }

        // 设置订单状态（未付款）
        order.setStatus(OrderConstants.OrderStatusEnum.CREATE_NEW.getCode());
        // 设置自动收货为15天
        order.setAutoConfirmDay(OrderConstants.AUTO_CONFIRM_DAY);
        // 设置订单删除状态为未删除
        order.setDeleteStatus(Constants.NO);

        return order;
    }


    /**
     * 构建所有订单项信息
     *
     * @param
     * @return
     */
    private List<OrderItemEntity> buildOrderItems(String orderSn) {
        MyResult<List<OrderItemDTO>> currentUserCartItems = cartFeignService.getCurrentUserCartItems();
        List<OrderItemDTO> cartItems = currentUserCartItems.getData();

        if (CollectionUtils.isEmpty(cartItems)) {
            return null;
        }

        return cartItems.stream().map(orderItemVO -> {
            OrderItemEntity orderItem = buildOrderItem(orderItemVO);
            orderItem.setOrderSn(orderSn);
            return orderItem;
        }).collect(Collectors.toList());
    }

    /**
     * 构建订单项信息
     *
     * @param orderItemDTO
     * @return
     */
    private OrderItemEntity buildOrderItem(OrderItemDTO orderItemDTO) {
        OrderItemEntity orderItem = new OrderItemEntity();

        // 商品的spu信息
        MyResult<SpuInfoDTO> spuInfoResult = productFeignService.getSpuInfoBySkuId(orderItemDTO.getSkuId());
        if (spuInfoResult.getSuccess()) {
            SpuInfoDTO spuInfo = spuInfoResult.getData();
            orderItem.setSpuId(spuInfo.getId());
            orderItem.setSpuName(spuInfo.getSpuName());
            orderItem.setSpuPic(spuInfo.getImage());
            orderItem.setCategoryId(spuInfo.getCategoryId());
            orderItem.setBrandId(spuInfo.getBrandId());
        }

        // 商品的sku信息
        orderItem.setSkuId(orderItemDTO.getSkuId());
        orderItem.setSkuName(orderItemDTO.getTitle());
        orderItem.setSkuPic(orderItemDTO.getImage());
        orderItem.setSkuPrice(orderItemDTO.getPrice());
        orderItem.setSkuQuantity(orderItemDTO.getCount());
        orderItem.setSkuAttrsValues(orderItem.getSkuAttrsValues());

        // 积分信息
        orderItem.setGiftGrowth(orderItemDTO.getPrice().multiply(new BigDecimal(orderItem.getSkuQuantity().toString())).intValue());
        orderItem.setGiftIntegration(orderItemDTO.getPrice().multiply(new BigDecimal(orderItem.getSkuQuantity().toString())).intValue());

        // 订单项价格信息
        orderItem.setPromotionAmount(new BigDecimal("0"));
        orderItem.setCouponAmount(new BigDecimal("0"));
        orderItem.setIntegrationAmount(new BigDecimal("0"));
        BigDecimal originalPrice = orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuQuantity().toString()));
        BigDecimal realPrice = originalPrice.subtract(orderItem.getPromotionAmount()).subtract(orderItem.getCouponAmount())
                .subtract(orderItem.getIntegrationAmount());
        orderItem.setRealAmount(realPrice);

        return orderItem;
    }


    /**
     * 计算价格、积分相关信息
     *
     * @param order
     * @param orderItems
     */
    private void computePrice(OrderEntity order, List<OrderItemEntity> orderItems) {

        // 订单总金额以及其他优惠金额
        BigDecimal totalPrice = new BigDecimal("0.0");
        BigDecimal promotionPrice = new BigDecimal("0.0");
        BigDecimal couponPrice = new BigDecimal("0.0");
        BigDecimal integrationPrice = new BigDecimal("0.0");
        // 积分和成长值
        Integer giftIntegration = 0;
        Integer giftGrowth = 0;

        for (OrderItemEntity orderItem : orderItems) {
            totalPrice = totalPrice.add(orderItem.getRealAmount());
            promotionPrice = promotionPrice.add(orderItem.getPromotionAmount());
            couponPrice = couponPrice.add(orderItem.getCouponAmount());
            integrationPrice = integrationPrice.add(orderItem.getIntegrationAmount());

            giftIntegration += orderItem.getGiftIntegration();
            giftGrowth += orderItem.getGiftGrowth();
        }
        order.setTotalAmount(totalPrice);
        order.setPromotionAmount(promotionPrice);
        order.setCouponAmount(couponPrice);
        order.setIntegrationAmount(integrationPrice);
        order.setIntegration(giftIntegration);
        order.setGrowth(giftGrowth);

        // 应付总额 = 订单总金额 + 运费
        order.setPayAmount(totalPrice.add(order.getFreightAmount()));
    }

}