package com.laughingather.gulimall.ware.consumer;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.api.ResultCodeEnum;
import com.laughingather.gulimall.common.constant.OrderConstants;
import com.laughingather.gulimall.common.constant.WareConstants;
import com.laughingather.gulimall.common.entity.StockDetailDTO;
import com.laughingather.gulimall.common.entity.StockLockedDTO;
import com.laughingather.gulimall.ware.entity.WareOrderTaskDetailEntity;
import com.laughingather.gulimall.ware.entity.WareOrderTaskEntity;
import com.laughingather.gulimall.ware.feign.entity.Order;
import com.laughingather.gulimall.ware.feign.service.OrderFeignService;
import com.laughingather.gulimall.ware.service.WareOrderTaskDetailService;
import com.laughingather.gulimall.ware.service.WareOrderTaskService;
import com.laughingather.gulimall.ware.service.WareSkuService;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * 消息消费端
 *
 * @author：laughingather
 * @create：2021-10-26 2021/10/26
 */
@Log4j2
@Component
@RabbitListener(queues = "stock.release.stock.queue")
public class UnlockStockService {

    @Autowired
    private WareOrderTaskService wareOrderTaskService;
    @Autowired
    private WareOrderTaskDetailService wareOrderTaskDetailService;
    @Autowired
    private WareSkuService wareSkuService;

    @Autowired
    public OrderFeignService orderFeignService;

    /**
     * 解锁锁定库存
     * <p>
     * 拿到消息后去数据库查询是否存在该库存锁定清单
     * 如果存在则证明库存锁定没有问题，还需要判断订单状态
     * 如果订单不存在或者订单取消才需要执行需要执行库存解锁任务，订单完成则不需要执行库存解锁任务
     * 如果不存在则表示可能执行业务过程中出现异常，持久层数据进行了回滚，这种情况直接签收消息即可
     *
     * @param stockLockedDTO
     * @param message
     * @param channel
     */
    @RabbitHandler
    public void releaseStockLock(StockLockedDTO stockLockedDTO, Message message, Channel channel) throws IOException {

        try {
            unlockStock(stockLockedDTO);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(e);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }

    }


    /**
     * 解锁库存
     *
     * @param stockLockedDTO
     */
    private void unlockStock(StockLockedDTO stockLockedDTO) {
        StockDetailDTO detail = stockLockedDTO.getDetail();
        WareOrderTaskDetailEntity wareOrderTaskDetail = wareOrderTaskDetailService.getById(detail.getId());

        // 如果库存锁定清单为null则直接签收消息
        if (wareOrderTaskDetail == null) {
            return;
        }

        // 如果库存锁定清单状态不为锁定则直接签收消息
        if (!Objects.equals(WareConstants.StockLockEnum.LOCKED.getCode(), wareOrderTaskDetail.getLockStatus())) {
            return;
        }

        // 远程获取订单状态
        WareOrderTaskEntity wareOrderTask = wareOrderTaskService.getById(wareOrderTaskDetail.getTaskId());
        String orderSn = wareOrderTask.getOrderSn();
        MyResult<Order> orderResult = orderFeignService.getOrderByOrderSn(orderSn);

        // 如果远程失败则抛出异常
        if (!Objects.equals(ResultCodeEnum.SUCCESS.getCode(), orderResult.getCode())) {
            throw new RuntimeException("远程调用失败");
        }

        Order order = orderResult.getData();
        // 订单不存在解锁库存
        if (order == null) {
            wareSkuService.unlockStock(detail.getSkuId(), detail.getWareId(), detail.getSkuNum(), detail.getId());
            return;
        }

        // 订单状态取消解锁库存
        if (Objects.equals(OrderConstants.OrderStatusEnum.CANCLED.getCode(), order.getStatus())) {
            wareSkuService.unlockStock(detail.getSkuId(), detail.getWareId(), detail.getSkuNum(), detail.getId());
        }
    }

}

