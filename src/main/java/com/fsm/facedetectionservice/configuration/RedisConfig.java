//package com.fsm.facedetectionservice.configuration;
//
//import com.fsm.facedetectionservice.data.model.FileInfo;
//import com.fsm.facedetectionservice.data.redis.RedisMessagePublisher;
//import com.fsm.facedetectionservice.data.redis.RedisMessageSubscriber;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
//import org.springframework.data.redis.serializer.GenericToStringSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    public RedisTemplate<String, Object> redisTemplate() {
//        final var template = new RedisTemplate<String, Object>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        template.setValueSerializer(
//                new GenericToStringSerializer<>(Object.class)
//        );
//        return template;
//    }
//
//    @Bean
//    MessageListenerAdapter messageListener() {
//        return new MessageListenerAdapter(
//                new RedisMessageSubscriber()
//        );
//    }
//
//    @Bean
//    RedisMessageListenerContainer redisContainer() {
//        final var container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(jedisConnectionFactory());
//        container.addMessageListener(messageListener(), topic());
//        return container;
//    }
//
//    RedisMessagePublisher<String, Object> redisPublisher() {
//        return new RedisMessagePublisher<>(redisTemplate(), topic());
//    }
//
//    @Bean
//    ChannelTopic topic() {
//        return new ChannelTopic("pubsub:queue");
//    }
//
//}
