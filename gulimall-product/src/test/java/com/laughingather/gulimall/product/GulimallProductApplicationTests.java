package com.laughingather.gulimall.product;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.dao.AttrGroupDao;
import com.laughingather.gulimall.product.entity.to.SpuBoundTO;
import com.laughingather.gulimall.product.entity.vo.ItemSaleAttrVO;
import com.laughingather.gulimall.product.entity.vo.SpuItemGroupAttrVO;
import com.laughingather.gulimall.product.feign.service.CouponFeignService;
import com.laughingather.gulimall.product.service.SkuSaleAttrValueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    private CouponFeignService couponFeignService;
    @Autowired
    private AttrGroupDao attrGroupDao;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFeign() {
        SpuBoundTO build = SpuBoundTO.builder().spuId(1L).growBounds(BigDecimal.ONE).buyBounds(BigDecimal.TEN).build();
        MyResult<Void> myResult = couponFeignService.saveSpuBounds(build);

        System.out.println("执行结果" + myResult);
    }


    @Test
    public void testAttrGroupDao() {
        List<SpuItemGroupAttrVO> attrGroupWithAttrsBySpuId = attrGroupDao.getAttrGroupWithAttrsBySpuId(225L, 7L);
        attrGroupWithAttrsBySpuId.stream().forEach(System.out::println);
    }

    @Test
    public void testSkuSaleAttrValueService() {
        List<ItemSaleAttrVO> saleAttrsBySpuId = skuSaleAttrValueService.getSaleAttrsBySpuId(7L);
        saleAttrsBySpuId.stream().forEach(System.out::println);
    }

}
