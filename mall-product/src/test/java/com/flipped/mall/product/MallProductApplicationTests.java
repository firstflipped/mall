package com.flipped.mall.product;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.product.dao.AttrGroupDao;
import com.flipped.mall.product.entity.dto.SpuBoundDTO;
import com.flipped.mall.product.entity.vo.ItemSaleAttrVO;
import com.flipped.mall.product.entity.vo.SpuItemAttrGroupWithAttrVO;
import com.flipped.mall.product.feign.service.CouponFeignService;
import com.flipped.mall.product.service.SkuSaleAttrValueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class MallProductApplicationTests {

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
        SpuBoundDTO build = SpuBoundDTO.builder().spuId(1L).growBounds(BigDecimal.ONE).buyBounds(BigDecimal.TEN).build();
        MyResult<Void> myResult = couponFeignService.saveSpuBounds(build);

        System.out.println("执行结果" + myResult);
    }


    @Test
    public void testAttrGroupDao() {
        List<SpuItemAttrGroupWithAttrVO> attrGroupWithAttrsBySpuId = attrGroupDao.getAttrGroupWithAttrsBySpuId(225L, 7L);
        attrGroupWithAttrsBySpuId.stream().forEach(System.out::println);
    }

    @Test
    public void testSkuSaleAttrValueService() {
        List<ItemSaleAttrVO> saleAttrsBySpuId = skuSaleAttrValueService.getSaleAttrsBySpuId(7L);
        saleAttrsBySpuId.stream().forEach(System.out::println);
    }

}
