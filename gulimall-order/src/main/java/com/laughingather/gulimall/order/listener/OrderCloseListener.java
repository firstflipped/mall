package com.laughingather.gulimall.order.listener;

import com.laughingather.gulimall.order.entity.OrderEntity;
import com.laughingather.gulimall.order.service.OrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 订单关闭监听
 *
 * @author：laughingather
 * @create：2021-10-26 2021/10/26
 */
@Slf4j
@Component
@RabbitListener(queues = "order.release.order.queue")
public class OrderCloseListener {

    @Autowired
    private OrderService orderService;

    @RabbitHandler
    public void releaseOrder(OrderEntity order, Message message, Channel channel) throws IOException {
        try {
            orderService.closeOrder(order.getId());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }

    }

}

