package com.laughingather.gulimall.product.web;

import com.laughingather.gulimall.product.entity.vo.SkuItemVO;
import com.laughingather.gulimall.product.service.SkuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品详情页
 *
 * @author：laughingather
 * @create：2021-06-04 21:22
 */
@Slf4j
@Controller
public class ItemController {

    @Autowired
    private SkuInfoService skuInfoService;

    @GetMapping("/{skuId}.html")
    public String itemPage(@PathVariable("skuId") Long skuId, Model model) {
        log.info("商品id是{}", skuId);
        SkuItemVO skuItemVO = skuInfoService.getSkuItemById(skuId);
        model.addAttribute("skuItemVO", skuItemVO);
        return "item";
    }

}
