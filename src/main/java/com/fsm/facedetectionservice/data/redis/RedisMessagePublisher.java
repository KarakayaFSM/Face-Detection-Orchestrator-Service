//package com.fsm.facedetectionservice.data.redis;
//
//import lombok.AllArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class RedisMessagePublisher<T, T1> {
//
//    private final RedisTemplate<T, T1> redisTemplate;
//    private final ChannelTopic topic;
//
//    public void publish(final String message) {
//        redisTemplate.convertAndSend(topic.getTopic(), message);
//    }
//}
