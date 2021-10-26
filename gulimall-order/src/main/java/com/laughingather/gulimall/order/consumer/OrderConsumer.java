package com.laughingather.gulimall.order.consumer;

import com.laughingather.gulimall.common.utils.JsonUtil;
import com.laughingather.gulimall.order.entity.OrderEntity;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 订单消费者
 *
 * @author：laughingather
 * @create：2021-10-25 2021/10/25
 */
@Component
public class OrderConsumer {

    @RabbitListener(queues = "order.release.order.queue")
    public void onMessage(OrderEntity order, Channel channel, Message message) throws IOException {
        System.out.println("收到消息：" + JsonUtil.obj2String(order));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}

