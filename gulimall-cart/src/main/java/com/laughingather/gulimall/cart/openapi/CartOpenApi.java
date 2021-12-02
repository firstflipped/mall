package com.laughingather.gulimall.cart.openapi;

import com.laughingather.gulimall.cart.entity.vo.CartItemVO;
import com.laughingather.gulimall.cart.service.CartService;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车提供第三方调用接口
 *
 * @author：laughingather
 * @create：2021-10-18 2021/10/18
 */
@RestController
@RequestMapping("/openapi/cart")
public class CartOpenApi {

    @Resource
    private CartService cartService;

    /**
     * 获取当前用户的购物车项
     *
     * @return
     */
    @GetMapping("/current-user-cart-items")
    MyResult<List<CartItemVO>> getCurrentUserCartItems() {
        List<CartItemVO> cartItemVOList = cartService.getCurrentUserCartItems();
        return MyResult.success(cartItemVOList);
    }

}

