package com.itdan.shopmall.spring.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * ActiveMQ消息插件配置
 */
@Configuration
public class ActiveMQConfig {

    @Value("${topicName}")
    private String topicName;

    @Value("${queueName}")
    private String queueName;

    @Value("${spring.activemq.user}")
    private String user;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.broker-url}")
    private String brokerURL;

    @Value("${spring.activemq.pool.max-connections}")
    private String maxConnections;


    //广播形式
    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topicName);
    }

   //点到点形式
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(queueName);
    }

    //真正可以产生Connection的ConnectionFactory，由对应的 JMS服务厂商提供
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        return new ActiveMQConnectionFactory(user,password,brokerURL);
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //设置为发布订阅方式, 默认情况下使用的生产消费者方式
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    public SingleConnectionFactory singleConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
        SingleConnectionFactory singleConnectionFactory=new SingleConnectionFactory();
        singleConnectionFactory.setTargetConnectionFactory(activeMQConnectionFactory);
        return singleConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(SingleConnectionFactory singleConnectionFactory){
        JmsTemplate jmsTemplate=new JmsTemplate();
        jmsTemplate.setConnectionFactory(singleConnectionFactory);
        return jmsTemplate;
    }

    @Bean
    public ActiveMQTopic activeMQTopic(){
        ActiveMQTopic activeMQTopic=new ActiveMQTopic("itemAddTopic");
        return activeMQTopic;
    }


}
