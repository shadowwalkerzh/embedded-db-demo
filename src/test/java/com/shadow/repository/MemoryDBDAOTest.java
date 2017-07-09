package com.shadow.repository;

import com.alibaba.fastjson.JSON;
import com.shadow.AbstractTest;
import com.shadow.ApplicationRunner;
import com.shadow.config.UnitTestMongoConfig;
import com.shadow.config.UnitTestRedisConfig;
import com.shadow.entity.Command;
import com.shadow.repository.jpa.JpaDAO;
import com.shadow.repository.mongo.MongoDAO;
import com.shadow.repository.redis.RedisDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import redis.embedded.RedisServer;

import java.util.Date;
import java.util.List;

@Slf4j
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest(classes = {ApplicationRunner.class, UnitTestMongoConfig.class, UnitTestRedisConfig.class})
public class MemoryDBDAOTest extends AbstractTest {

    @Autowired
    private JpaDAO jpaRepository;

    @Autowired
    private MongoDAO mongoRepository;

    @Autowired
    private RedisDAO redisRepository;

    /**
     * 以下是用内存数据库测试
     */

    @Test
    public void testEmbeddedJpa() {
        Command command = new Command();
        command.setName("[in-memory] docker images --format 'Id=67'");
        command.setCreateTime(new Date());
        jpaRepository.save(command);
        List<Command> all = jpaRepository.findAll();
        log.info("embedded jpa result: {}", JSON.toJSONString(all));
        Assert.assertNotNull(all);
        Assert.assertTrue(all.contains(command));
    }

    @Test
    public void testEmbeddedMongo() {
        Command command = new Command();
        command.setName("[in-memory] docker ps -a");
        command.setCreateTime(new Date());
        mongoRepository.save(command);
        log.info("embedded mongo result: {}", JSON.toJSONString(mongoRepository.findAll()));
        Assert.assertTrue(mongoRepository.findAll().size() == 1);
    }

    @Test
    public void testEmbeddedRedis() {
        Command command = new Command();
        command.setName("[in-memory] docker restart 8988");
        command.setCreateTime(new Date());
        redisRepository.save(command);
        log.info("embedded redis result: {}", JSON.toJSONString(redisRepository.findAll()));
        Assert.assertTrue(redisRepository.findAll().iterator().hasNext());
    }

    static private RedisServer redisServer;

    @Autowired
    public void setRedisServer(RedisServer rs) {
        redisServer = rs;
    }

    @AfterClass
    static public void afterClass() {
        redisServer.stop();
    }

}
