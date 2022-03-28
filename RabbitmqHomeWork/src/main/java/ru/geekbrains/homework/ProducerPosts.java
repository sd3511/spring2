package ru.geekbrains.homework;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerPosts {
    private final static String POST_PHP = "php some message";
    private final static String POST_JAVA = "java some message";
    private final static String POST_CPP = "cpp some message";
    private final static String POST_PYTHON = "python some message";
    private final static String POST_PASCAL = "pascal some message";
    private final static String EXCHANGER_NAME = "hello_exchanger";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGER_NAME, BuiltinExchangeType.DIRECT);


            String[] keyAndMsg = returnRoutinkeyAndMessage(POST_CPP);
          //  String[] keyAndMsg = returnRoutinkeyAndMessage(POST_PHP);
           // String[] keyAndMsg = returnRoutinkeyAndMessage(POST_JAVA);
            //String[] keyAndMsg = returnRoutinkeyAndMessage(POST_PYTHON);
            //String[] keyAndMsg = returnRoutinkeyAndMessage(POST_PASCAL);
            channel.basicPublish(EXCHANGER_NAME, keyAndMsg[0], null, keyAndMsg[1].getBytes());
            System.out.println(" [x] Sent " +" routing key '"+ keyAndMsg[0]+"' msg:'" + keyAndMsg[1] + "'");
        }
    }



    static String[] returnRoutinkeyAndMessage(String post) {
        String[] s = post.split(" ", 2);
        return s;
    }
}
