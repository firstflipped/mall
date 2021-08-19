package com.laughingather.gulimall.cart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 购物车路由层
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/cart.html")
    public String cartListPage() {
        return "cartList";
    }


    /**
     * 添加商品到购物车
     *
     * @return
     */
    @GetMapping("/add-to-cart")
    public String addToCart() {
        return "success";
    }

}

