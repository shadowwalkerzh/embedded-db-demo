package com.shadow.repository;

import com.alibaba.fastjson.JSON;
import com.shadow.AbstractTest;
import com.shadow.entity.Command;
import com.shadow.repository.jpa.JpaDAO;
import com.shadow.repository.mongo.MongoDAO;
import com.shadow.repository.redis.RedisDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Slf4j
public class LocalDBDAOTest extends AbstractTest {

    @Autowired
    private JpaDAO jpaRepository;

    @Autowired
    private MongoDAO mongoRepository;

    @Autowired
    private RedisDAO redisRepository;

    /**
     * 这三个测试是本地数据库测试（docker启动或本地安装db server启动均可）
     */
    @Test
    public void testJpaRepository() {
        Command command = new Command();
        command.setName("docker images");
        command.setCreateTime(new Date());
        jpaRepository.save(command);
        List<Command> all = jpaRepository.findAll();
        log.info("jpa result: {}", JSON.toJSONString(all));
        Assert.assertNotNull(all);
        Assert.assertTrue(all.contains(command));
    }

    @Test
    public void testMongoRepository() {
        Command command = new Command();
        command.setName("docker ps");
        command.setCreateTime(new Date());
        mongoRepository.save(command);
        List<Command> all = mongoRepository.findAll();
        log.info("mongo result: {}", JSON.toJSONString(all));
        Assert.assertNotNull(all);
        Assert.assertTrue(all.contains(command));
    }

    @Test
    public void testRedisRepository() {
        Command command = new Command();
        command.setName("docker run 792");
        command.setCreateTime(new Date());
        redisRepository.save(command);
        Iterable<Command> all = redisRepository.findAll();
        log.info("redis result: {}", JSON.toJSONString(all));
        Assert.assertNotNull(all);
        Assert.assertTrue(all.iterator().hasNext());
    }

}
