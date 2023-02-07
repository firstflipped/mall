package com.laughingather.gulimall.order;

import cn.hutool.core.util.IdUtil;
import com.flipped.mall.common.util.JsonUtil;
import com.laughingather.gulimall.order.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class GulimallOrderApplicationTests {

    @Resource
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

        rabbitTemplate.convertAndSend("order.event.exchange", "order.create.order", JsonUtil.obj2String(order));
        System.out.println("发送成功");
    }

}
