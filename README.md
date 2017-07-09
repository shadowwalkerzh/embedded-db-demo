###embedded-db-demo
该项目主要是实现Java项目的DAO层独立进行单元测试，不依赖其它环境，也不需要本地安装数据库实例，主要包括的常用数据库有：MYSQL，MONGODB， REDIS，项目基于Spring Boot + JPA实现。
 - embedded mongodb单元测试mongodb步骤：
   - 需要添加依赖
    ```        
    <dependency>
       <groupId>de.flapdoodle.embed</groupId>
       <artifactId>de.flapdoodle.embed.mongo</artifactId>
       <version>2.0.0</version>
       <scope>test</scope>
   </dependency>
   <dependency>
       <groupId>cz.jirutka.spring</groupId>
       <artifactId>embedmongo-spring</artifactId>
       <version>1.3.1</version>
   </dependency>
   ```
   - 创建MongoTemplate实例，可以设置名称，根据业务中的自定义名称来相应的mock.有两种方式可以实现，一是加一个mongo template的定义configuration，另一种是继承mongo配置的抽象类，并重写mongo和getDatabaseName方法。
   - 在测试类中加载test mongo的测试类即可，如：@SpringBootTest(classes = {ApplicationRunner.class, UnitTestMongoConfig.class})
 - h2行单元测试mysql步骤：
   - 添加依赖
    ```
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>1.4.193</version>
        <scope>test</scope>
    </dependency>
    ```
   - 在测试类上加上注解即可mock mysql: @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
 - embedded redis 单元测试redis:
   - 添加依赖
    ```
    <dependency>
        <groupId>com.github.kstyrc</groupId>
        <artifactId>embedded-redis</artifactId>
        <version>0.6</version>
        <scope>test</scope>
    </dependency>
    ```
   - 自定义redis的测试配置，UnitTestRedisConfig
   - 在测试类中加载配置：@SpringBootTest(classes = {ApplicationRunner.class, UnitTestRedisConfig.class})，在要测试完成后关闭redis服务。


