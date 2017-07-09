package com.shadow.repository.redis;

import com.shadow.entity.Command;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface RedisDAO extends KeyValueRepository<Command, Long> {
}
