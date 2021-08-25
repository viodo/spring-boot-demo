package org.viodo.activemq.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/24 13:59
 */
@Slf4j
@Component
public class ConsumerService {

    /**
     * 接收队列消息
     *
     * @param message message
     * @param session session
     * @return void
     * @author xc.chen
     * @date 2021/8/25 9:22
     */
    @JmsListener(destination = "order.queue", containerFactory = "topicListener")
    public void receiveQueue(ActiveMQMessage message, Session session) throws JMSException {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            log.info("topic接收到消息：{}, 类型为：{}", textMessage.getText(), textMessage.getJMSType());
            // 手动确认
            // message.acknowledge();
            // 重新发送
            // session.recover();
        }
    }
}
