package com.laughingather.gulimall.cart.web;

import com.laughingather.gulimall.cart.pojo.vo.CartItemVO;
import com.laughingather.gulimall.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 购物车路由层
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

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
    public String addToCart(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num, Model model) {
        CartItemVO cartItemVO = cartService.addToCart(skuId, num);
        model.addAttribute("item", cartItemVO);
        return "success";
    }

}

