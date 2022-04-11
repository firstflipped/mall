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
 * 购物车服务对外开放接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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

