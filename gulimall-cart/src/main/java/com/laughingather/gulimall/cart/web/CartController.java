package com.laughingather.gulimall.cart.web;

import com.laughingather.gulimall.cart.entity.vo.CartItemVO;
import com.laughingather.gulimall.cart.entity.vo.CartVO;
import com.laughingather.gulimall.cart.service.CartService;
import com.laughingather.gulimall.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * 购物车路由层
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
 */
@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * 购物车列表
     *
     * @param model
     * @return
     */
    @GetMapping("/cart.html")
    public String cartListPage(Model model) throws ExecutionException, InterruptedException {
        CartVO cartVO = cartService.getCart();
        log.info(JsonUtil.obj2String(cartVO));
        model.addAttribute("cart", cartVO);
        return "cartList";
    }


    /**
     * 添加商品到购物车
     *
     * @param skuId              商品id
     * @param num                商品数量
     * @param redirectAttributes 重定向参数
     *                           redirectAttributes.addAttribute()：将数据放在url后面
     *                           redirectAttributes.addFlashAttribute()：将数据放在session里面可以在页面取出，但是只能取一次
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num, RedirectAttributes redirectAttributes) throws ExecutionException, InterruptedException {
        cartService.addToCart(skuId, num);
        redirectAttributes.addAttribute("skuId", skuId);

        // 重定向防止无限次提交
        return "redirect:http://cart.gulimall.com/cart/add-to-cart-success.html";
    }

    /**
     * 跳转到成功页
     *
     * @param skuId
     * @param model
     * @return
     */
    @GetMapping("/add-to-cart-success.html")
    public String addToCartSuccessPage(@RequestParam("skuId") Long skuId, Model model) {
        CartItemVO cartItemVO = cartService.getCartItem(skuId);
        model.addAttribute("item", cartItemVO);
        // 重定向到成功页面，再次查询购物车数据即可
        return "success";
    }


    /**
     * 选中购物项
     *
     * @param skuId
     * @param check
     */
    @GetMapping("check-item")
    public String checkItem(@RequestParam("skuId") Long skuId, @RequestParam("check") Integer check) {
        cartService.checkItem(skuId, check);

        // 重定向到购物车列表页
        return "redirect:http://cart.gulimall.com/cart/cart.html";
    }


    /**
     * 购物项数量修改
     *
     * @param skuId
     * @param count
     */
    @GetMapping("count-item")
    public String countItem(@RequestParam("skuId") Long skuId, @RequestParam("count") Integer count) {
        cartService.changeItemCount(skuId, count);

        // 重定向到购物车列表页
        return "redirect:http://cart.gulimall.com/cart/cart.html";
    }


    /**
     * 删除购物车项
     *
     * @param skuId
     */
    @GetMapping("delete-item")
    public String deleteItem(@RequestParam("skuId") Long skuId) {
        cartService.deleteItem(skuId);

        // 重定向到购物车列表页
        return "redirect:http://cart.gulimall.com/cart/cart.html";
    }


}

