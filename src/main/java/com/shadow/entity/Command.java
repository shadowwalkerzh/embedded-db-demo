package com.shadow.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Document
@RedisHash
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field("_id")
    private String id;

    private String name;

    private Date createTime;

}
