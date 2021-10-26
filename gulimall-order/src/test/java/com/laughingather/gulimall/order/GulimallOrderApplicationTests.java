package com.laughingather.gulimall.order;

import cn.hutool.core.util.IdUtil;
import com.laughingather.gulimall.order.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallOrderApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    void contextLoads() {
    }


    @Test
    public void sendMessage() {
        OrderEntity order = new OrderEntity();
        order.setId(1L);
        order.setOrderSn(IdUtil.simpleUUID());
        order.setMemberId(1L);

        rabbitTemplate.convertAndSend("order.event.exchange", "order.create.order", order);
        System.out.println("发送成功");
    }

}
