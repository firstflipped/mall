package com.laughingather.gulimall.ware.config;

import com.laughingather.gulimall.common.constant.WareConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * rabbitmq消息队列配置
 * <p>
 * 创建交换机、队列等是在第一次连接到rabbitmq的时候完成的
 *
 * @author：laughingather
 * @create：2021-10-24 2021/10/24
 */
@Slf4j
@Configuration
public class MyRabbitConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 自定义化模板配置类
     * 实现消息发送回调功能
     *
     * @return
     * @PostConstruct 表示在当前对象创建完之后执行
     */
    @PostConstruct
    public void initRabbitTemplate() {

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
    }


    /**
     * 消息转换器
     * 把消息转换为json格式
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    /**
     * 库存交换机
     *
     * @return
     */
    @Bean
    public Exchange stockEventExchange() {
        return ExchangeBuilder.topicExchange(WareConstants.MQEnum.EXCHANGE.getName()).durable(true).build();
    }


    /**
     * 库存释放队列
     *
     * @return
     */
    @Bean
    public Queue stockReleaseStockQueue() {
        return QueueBuilder.durable(WareConstants.MQEnum.RELEASE_QUEUE.getName()).build();
    }


    /**
     * 库存延时队列
     *
     * @return
     */
    @Bean
    public Queue stockDelayQueue() {
        return QueueBuilder.durable(WareConstants.MQEnum.DELAY_QUEUE.getName())
                .deadLetterExchange(WareConstants.MQEnum.EXCHANGE.getName())
                .deadLetterRoutingKey("stock.release")
                .ttl(2 * 60 * 1000)
                .build();
    }


    /**
     * 库存释放队列绑定关系
     *
     * @return
     */
    @Bean
    public Binding stockReleaseBinding() {
        return BindingBuilder.bind(stockReleaseStockQueue())
                .to(stockEventExchange())
                .with(WareConstants.MQEnum.RELEASE_ROUTING_KEY.getName())
                .noargs();
    }


    /**
     * 库存延时队列绑定关系
     *
     * @return
     */
    @Bean
    public Binding stockLockedBinding() {
        return BindingBuilder.bind(stockDelayQueue())
                .to(stockEventExchange())
                .with(WareConstants.MQEnum.LOCKED_ROUTING_KEY.getName())
                .noargs();
    }

}

