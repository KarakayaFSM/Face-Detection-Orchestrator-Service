//package com.fsm.facedetectionservice.data.redis;
//
//import org.springframework.data.redis.connection.Message;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RedisMessageSubscriber {
//    public static List<String> messageList = new ArrayList<String>();
//
//    public void onMessage(final Message message, final byte[] pattern) {
//        messageList.add(message.toString());
//        System.out.println("Message received: " + new String(message.getBody()));
//    }
//}
