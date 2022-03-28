package ru.geekbrains.homework;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsumerContent {
    private static final String EXCHANGE_NAME = "hello_exchanger";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String queueName = channel.queueDeclare().getQueue();

        new Thread(() -> {
            String s = "";
            while (true) {
                try {
                    if ((s = reader.readLine()).startsWith("exit")) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s.startsWith("set_topic")) {
                    s = s.substring(10);
                    TopicName topicName = TopicName.valueOf(s);
                    switch (topicName) {
                        case cpp:
                            try {
                                channel.queueBind(queueName, EXCHANGE_NAME, "cpp");
                                System.out.println("Subscribe on cpp");
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case php:
                            try {
                                channel.queueBind(queueName, EXCHANGE_NAME, "php");
                                System.out.println("Subscribe on php");
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case java:
                            try {
                                channel.queueBind(queueName, EXCHANGE_NAME, "java");
                                System.out.println("Subscribe on java");
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case pascal:
                            try {
                                channel.queueBind(queueName, EXCHANGE_NAME, "pascal");
                                System.out.println("Subscribe on pascal");
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case python:
                            try {
                                channel.queueBind(queueName, EXCHANGE_NAME, "python");
                                System.out.println("Subscribe on python");
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                }
                if (s.startsWith("unset_topic")) {
                    s = s.substring(12);
                    TopicName topicName = TopicName.valueOf(s);
                    switch (topicName) {
                        case cpp:
                            try {
                                channel.queueUnbind(queueName, EXCHANGE_NAME, "cpp");
                                System.out.println("Unubscribe on cpp");
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case php:
                            try {
                                channel.queueUnbind(queueName, EXCHANGE_NAME, "php");
                                System.out.println("Unsubscribe on php");
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case java:
                            try {
                                channel.queueUnbind(queueName, EXCHANGE_NAME, "java");
                                System.out.println("Unsubscribe on java");
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case pascal:
                            try {
                                channel.queueUnbind(queueName, EXCHANGE_NAME, "pascal");
                                System.out.println("Unsubscribe on pascal");
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        case python:
                            try {
                                channel.queueUnbind(queueName, EXCHANGE_NAME, "python");
                                System.out.println("Unsubscribe on python");
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                }


            }
        }).start();


        System.out.println("QUEUE NAME: " + queueName);
        System.out.println(" [*] Waiting for messages (:");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");


        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }

    public enum TopicName {
        php,
        java,
        cpp,
        python,
        pascal;
    }
}
