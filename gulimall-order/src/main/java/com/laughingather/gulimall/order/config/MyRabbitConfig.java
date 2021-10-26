package com.laughingather.gulimall.order.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq消息队列配置
 *
 * @author：laughingather
 * @create：2021-10-24 2021/10/24
 */
@Slf4j
@Configuration
public class MyRabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        // 设置消息发送到服务器回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * 只要消息抵达交换机（Broke），ack就为true
             *
             * @param correlationData 当前消息的唯一关联数据（消息的唯一id）
             * @param ack 消息是否收到
             * @param cause 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    log.info("消息发送到服务器成功");
                } else {
                    log.info("消息发送到服务器失败");
                }
            }
        });

        // 设置消息发送到队列回调
        /**
         * 只要消息没有发送到指定的队列就触发这个失败回调
         *
         * @param message
         * @param replyCode
         * @param replyText
         * @param exchange
         * @param routingKey
         */
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息发送到队列失败");
        });

        // 设置消息转换器
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        return rabbitTemplate;
    }


    /**
     * 订单延时队列
     * <p>
     * 消息到达 order.delay.queue队列 一分钟之后会到达 order.release.order队列
     *
     * @return
     */
    @Bean
    public Queue orderDelayQueue() {
        // 自定义参数
        Map<String, Object> arguments = new HashMap<>(4);
        arguments.put("x-dead-letter-exchange", "order.event.exchange");
        arguments.put("x-dead-letter-routing-key", "order.release.order");
        arguments.put("x-message-ttl", 60000);

        return new Queue("order.delay.queue", true, false, false, arguments);
    }

    /**
     * 订单释放队列
     *
     * @return
     */
    @Bean
    public Queue orderReleaseOrderQueue() {
        return QueueBuilder.durable("order.release.order.queue").build();
    }

    /**
     * 订单交换机
     *
     * @return
     */
    @Bean
    public Exchange orderEventExchange() {
        return new TopicExchange("order.event.exchange", true, false);
    }

    /**
     * 订单延时队列绑定关系
     *
     * @return
     */
    @Bean
    public Binding orderCreateOrderBinding() {
        return new Binding("order.delay.queue", Binding.DestinationType.QUEUE, "order.event.exchange",
                "order.create.order", null);
    }

    /**
     * 订单释放队列绑定关系
     *
     * @return
     */
    @Bean
    public Binding orderReleaseOrderBinding() {
        return BindingBuilder.bind(orderReleaseOrderQueue()).to(orderEventExchange()).with("order.release.order").noargs();
    }

}

