package com.shadow.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

@Configuration
public class UnitTestRedisConfig {

    @Bean(destroyMethod = "stop")
    public RedisServer redisServer() {
        RedisServer.builder().reset();
        return RedisServer.builder().port(Protocol.DEFAULT_PORT).build();
    }

    @Bean
    @SneakyThrows
    public RedisTemplate redisTemplate() {
        if (!redisServer().isActive()) redisServer().start();
        RedisTemplate template = new RedisTemplate();
        JedisShardInfo info = new JedisShardInfo("localhost", Protocol.DEFAULT_PORT);
        template.setConnectionFactory(new JedisConnectionFactory(info));
        return template;
    }

}
