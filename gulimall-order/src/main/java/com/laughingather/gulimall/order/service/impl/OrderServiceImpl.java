package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.api.ResultCodeEnum;
import com.laughingather.gulimall.common.entity.MemberEntity;
import com.laughingather.gulimall.order.dao.OrderDao;
import com.laughingather.gulimall.order.entity.OrderEntity;
import com.laughingather.gulimall.order.entity.vo.ItemVO;
import com.laughingather.gulimall.order.entity.vo.MemberReceiveAddressVO;
import com.laughingather.gulimall.order.entity.vo.OrderConfirmVO;
import com.laughingather.gulimall.order.entity.vo.SkuHashStockVO;
import com.laughingather.gulimall.order.feign.CartFeignService;
import com.laughingather.gulimall.order.feign.MemberFeignService;
import com.laughingather.gulimall.order.feign.WareFeignService;
import com.laughingather.gulimall.order.interceptor.LoginUserInterceptor;
import com.laughingather.gulimall.order.service.OrderService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;


/**
 * 订单逻辑
 *
 * @author laughingather
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    private MemberFeignService memberFeignService;
    @Autowired
    private CartFeignService cartFeignService;
    @Autowired
    private WareFeignService wareFeignService;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public OrderConfirmVO confirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVO orderConfirmVO = new OrderConfirmVO();
        // 从拦截类中获取用户信息
        MemberEntity member = LoginUserInterceptor.loginUser.get();

        // 拿到主线程的请求信息，将请求信息设置到副线程里面（每一个线程都共享请求信息）
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        CompletableFuture<Void> addressCompletableFuture = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            // 1、远程调用会员服务获取收货地址信息
            MyResult<List<MemberReceiveAddressVO>> memberReceiveAddressVOResult = memberFeignService.listMemberReceiveAddress(member.getId());
            if (Objects.equals(ResultCodeEnum.SUCCESS.getCode(), memberReceiveAddressVOResult.getCode())) {
                List<MemberReceiveAddressVO> addresses = memberReceiveAddressVOResult.getData();
                orderConfirmVO.setAddresses(addresses);
            }
        }, threadPoolExecutor);

        CompletableFuture<Void> itemsCompletableFuture = CompletableFuture.runAsync(() -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            // 2、远程调用购物车服务获取购物车项信息
            MyResult<List<ItemVO>> currentUserCartItemsResult = cartFeignService.getCurrentUserCartItems();
            if (Objects.equals(ResultCodeEnum.SUCCESS.getCode(), currentUserCartItemsResult.getCode())) {
                List<ItemVO> items = currentUserCartItemsResult.getData();
                orderConfirmVO.setItems(items);
            }
        }, threadPoolExecutor).thenRunAsync(() -> {
            // 3、远程调用库存服务获取商品库存信息
            List<ItemVO> items = orderConfirmVO.getItems();
            if (CollectionUtils.isNotEmpty(items)) {
                List<Long> skuIds = items.stream().map(ItemVO::getSkuId).collect(Collectors.toList());
                List<SkuHashStockVO> skusHasStock = wareFeignService.getSkusHasStock(skuIds);
                for (ItemVO item : items) {
                    for (SkuHashStockVO skuHashStockVO : skusHasStock) {
                        if (item.getSkuId().equals(skuHashStockVO.getSkuId())) {
                            item.setHasStock(skuHashStockVO.getHasStock());
                        }
                    }
                }
            }
        }, threadPoolExecutor);

        // 3、查询会员优惠券信息，主要是会员积分
        Integer integration = member.getIntegration();
        orderConfirmVO.setIntegration(integration);

        CompletableFuture.allOf(addressCompletableFuture, itemsCompletableFuture).get();

        return orderConfirmVO;
    }
}