package com.itdan.shopmall.spring.config;

import com.itdan.shopmall.activemq.message.AddItemMessageLisener;
import com.itdan.shopmall.activemq.message.HtmlGenMessageLisener;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * 消息监听器
 */
@Configuration
public class MessageLisenerConfig {

    //添加商品监听器
    @Bean
    public DefaultMessageListenerContainer addItemMessageLisenter(SingleConnectionFactory singleConnectionFactory,
                                                                           ActiveMQTopic destination){
        DefaultMessageListenerContainer defaultMessageListenerContainer= new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(singleConnectionFactory);
        defaultMessageListenerContainer.setDestination(destination);
        defaultMessageListenerContainer.setMessageListener(new AddItemMessageLisener());
        return defaultMessageListenerContainer;
     }

     //生成静态界面监听器
    @Bean
    public DefaultMessageListenerContainer htmlGenMessageLisener(SingleConnectionFactory singleConnectionFactory,
                                                                  ActiveMQTopic destination){
        DefaultMessageListenerContainer defaultMessageListenerContainer= new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(singleConnectionFactory);
        defaultMessageListenerContainer.setDestination(destination);
        defaultMessageListenerContainer.setMessageListener(new HtmlGenMessageLisener());
        return defaultMessageListenerContainer;
    }
}
