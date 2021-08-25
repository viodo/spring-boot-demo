package org.viodo.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

/**
 * @author xc.chen
 * @version 1.0
 * @date 2021-08-24
 */
@Configuration
public class ActiveMqConfig {

    @Value("${spring.activemq.user}")
    private String userName;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.client-id}")
    private String clientId;

    @Value("${spring.activemq.maximum-redeliveries}")
    private Integer maximumRedeliveries;

    @Value("${spring.activemq.initial-redelivery-delay}")
    private Integer initialRedeliveryDelay;

    @Value("${spring.activemq.back-off-multiplier}")
    private Integer backOffMultiplier;

    @Value("${spring.activemq.maximum-redelivery-delay}")
    private Integer maximumRedeliveryDelay;

    @Value("${spring.activemq.use-collision-avoidance}")
    private Boolean useCollisionAvoidance;

    /**
     * 配置activeMQConnectionFactory连接工厂
     *
     * @return org.apache.activemq.ActiveMQConnectionFactory
     * @author xc.chen
     * @date 2021/8/25 11:35
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(userName, password, brokerUrl);
        // 设置重发机制
        RedeliveryPolicy policy = new RedeliveryPolicy();
        // 是否在每次尝试重新发送失败后，增长这个等待时间
        policy.setUseExponentialBackOff(Boolean.TRUE);
        // 消息处理失败重新处理次数,默认为6次
        policy.setMaximumRedeliveries(maximumRedeliveries);
        // 重发时间间隔,默认为1秒
        policy.setInitialRedeliveryDelay(initialRedeliveryDelay);
        // 设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
        policy.setMaximumRedeliveryDelay(maximumRedeliveryDelay);
        // 第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
        policy.setBackOffMultiplier(backOffMultiplier);
        // 是否避免消息碰撞
        policy.setUseCollisionAvoidance(useCollisionAvoidance);
        activeMQConnectionFactory.setRedeliveryPolicy(policy);
        return activeMQConnectionFactory;
    }

    /**
     * Queue模式配置
     *
     * @param activeMQConnectionFactory activeMQConnectionFactory
     * @return org.springframework.jms.config.JmsListenerContainerFactory<?>
     * @author xc.chen
     * @date 2021/8/25 11:56
     */
    @Bean("queueListener")
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        // 关闭事务
        factory.setSessionTransacted(false);
        // 手动确认消息
        factory.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(activeMQConnectionFactory);
        return factory;
    }

    /**
     * Topic模式配置
     *
     * @param activeMQConnectionFactory activeMQConnectionFactory
     * @return org.springframework.jms.config.JmsListenerContainerFactory<?>
     * @author xc.chen
     * @date 2021/8/25 11:56
     */
    @Bean("topicListener")
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory activeMQConnectionFactory) {
        // 设置为发布订阅模式, 默认情况下使用生产消费者方式
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory);
        factory.setPubSubDomain(true);
        factory.setSessionTransacted(false);
        // 手动确认消息
        factory.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        factory.setAutoStartup(true);
        // 开启持久化订阅
        factory.setSubscriptionDurable(true);
        factory.setClientId(clientId);
        return factory;
    }
}
