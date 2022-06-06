package com.laughingather.gulimall.order.config;

import com.laughingather.gulimall.common.constant.OrderConstants;
import com.laughingather.gulimall.common.constant.WareConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq消息队列配置
 * 最重要的是要保证数据的可靠性
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@Configuration
public class MyRabbitConfig {

    /**
     * 自定义化rabbitTemplate
     *
     * @param connectionFactory
     * @return
     */
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
                if (!ack) {
                    log.info("消息发送到服务器失败，消息内容为：" + correlationData.getReturned());
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
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.info("消息发送到队列失败，消息内容为：" + returnedMessage);
        });

        // 设置消息转换器
        rabbitTemplate.setMessageConverter(messageConverter());

        return rabbitTemplate;
    }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
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
        arguments.put("x-dead-letter-exchange", OrderConstants.EXCHANGE);
        arguments.put("x-dead-letter-routing-key", OrderConstants.RELEASE_ROUTING_KEY);
        arguments.put("x-message-ttl", 60000);

        return new Queue(OrderConstants.DELAY_QUEUE, true, false, false, arguments);
    }

    /**
     * 订单释放队列
     *
     * @return
     */
    @Bean
    public Queue orderReleaseOrderQueue() {
        return QueueBuilder.durable(OrderConstants.RELEASE_QUEUE).build();
    }

    /**
     * 订单交换机
     *
     * @return
     */
    @Bean
    public Exchange orderEventExchange() {
        return new TopicExchange(OrderConstants.EXCHANGE, true, false);
    }

    /**
     * 订单延时队列绑定关系
     *
     * @return
     */
    @Bean
    public Binding orderCreateOrderBinding() {
        return new Binding(OrderConstants.DELAY_QUEUE, Binding.DestinationType.QUEUE, OrderConstants.EXCHANGE,
                OrderConstants.CREATE_ROUTING_KEY, null);
    }

    /**
     * 订单释放队列绑定关系
     *
     * @return
     */
    @Bean
    public Binding orderReleaseOrderBinding() {
        return BindingBuilder.bind(orderReleaseOrderQueue()).to(orderEventExchange()).with(OrderConstants.RELEASE_ROUTING_KEY).noargs();
    }


    /**
     * 订单释放队列直接和库存释放队列绑定关系
     *
     * @return
     */
    @Bean
    public Binding orderReleaseOtherBinding() {
        return new Binding(WareConstants.MQEnum.RELEASE_QUEUE.getName(),
                Binding.DestinationType.QUEUE,
                OrderConstants.EXCHANGE,
                "order.release.other.#",
                null);
    }

}

