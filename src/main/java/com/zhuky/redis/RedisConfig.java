package com.zhuky.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {


    /**
     * RedisTemplate配置
     * @param connectionFactory
     * @return
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
//        System.out.println("实例化redisTemplate开始");
        // 配置redisTemplate
        System.out.println("redis conn：" + connectionFactory.getHostName() + " " + connectionFactory.getPort());
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());//key序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());//value序列化
        redisTemplate.setEnableTransactionSupport(true); //开启事务
        redisTemplate.afterPropertiesSet();
//        System.out.println("实例化redisTemplate结束。" + redisTemplate);
        return redisTemplate;
    }


}
