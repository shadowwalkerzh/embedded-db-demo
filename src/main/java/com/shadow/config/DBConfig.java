package com.shadow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.shadow.repository.jpa")
@EnableMongoRepositories(basePackages = "com.shadow.repository.mongo")
@EnableRedisRepositories(basePackages = "com.shadow.repository.redis")
public class DBConfig {
}
