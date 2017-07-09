package com.shadow.repository.mongo;

import com.shadow.entity.Command;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDAO extends MongoRepository<Command, String> {
}
