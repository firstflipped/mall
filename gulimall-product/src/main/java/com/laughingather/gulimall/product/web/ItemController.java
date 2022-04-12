package com.laughingather.gulimall.product.web;

import com.laughingather.gulimall.product.entity.vo.SkuItemVO;
import com.laughingather.gulimall.product.service.SkuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * 商品详情页
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@Controller
public class ItemController {

    @Resource
    private SkuInfoService skuInfoService;

    @GetMapping("/{skuId}.html")
    public String itemPage(@PathVariable("skuId") Long skuId, Model model) throws ExecutionException, InterruptedException {
        SkuItemVO skuItemVO = skuInfoService.getSkuItemById(skuId);
        model.addAttribute("skuItemVO", skuItemVO);
        return "item";
    }

}
