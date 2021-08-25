package org.viodo.activemq;

import org.apache.activemq.command.ActiveMQTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.TextMessage;
import java.util.UUID;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/24 14:00
 */
@SpringBootTest
public class ActiveMqTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void TestQueue() {
        ActiveMQTopic activeMQTopic = new ActiveMQTopic("order.queue");
        jmsTemplate.send(activeMQTopic, session -> {
            TextMessage textMessage = session.createTextMessage("test msg");
            textMessage.setJMSType("test type");
            textMessage.setStringProperty("Content-Type", "application/json");
            textMessage.setJMSPriority(1);
            textMessage.setJMSCorrelationID(UUID.randomUUID().toString());
            return textMessage;
        });
    }
}
