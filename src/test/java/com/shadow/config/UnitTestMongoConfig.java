package com.shadow.config;

import com.mongodb.Mongo;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class UnitTestMongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.db.name:test}")
    private String dbName;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new EmbeddedMongoBuilder()
                .version(Version.V3_3_1.asInDownloadPath())
                .bindIp(Network.getLocalHost().getHostAddress())
                .port(Network.getFreeServerPort())
                .build();
    }

}
