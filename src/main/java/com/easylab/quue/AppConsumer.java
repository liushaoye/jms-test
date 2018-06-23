package com.easylab.quue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/******************************
 * @author : liuyang
 * <p>ProjectName:jms-test  </p>
 * @ClassName :  AppProducer
 * @date : 2018/6/20 0020
 * @time : 20:46
 * @createTime 2018-06-20 20:46
 * @version : 2.0
 * @description :
 *
 *
 *
 *******************************/


public class AppConsumer {

    //连接地址
    public static final String url = "tcp://192.168.31.33:61616";

    // 连接名称
    public static final String queueName = "queue-test";


    public static void main(String[] args) throws JMSException {


        // 1. 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        // 2. 创建Connection
        Connection connection = connectionFactory.createConnection();

        // 3. 启动连接
        connection.start();

        // 4. 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5. 创建一个目标
        Destination destination = session.createQueue(queueName);

        // 6. 创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;

                try {
                    System.out.println("接受消息" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 9. 关闭连接
//        connection.close();

    }


}
