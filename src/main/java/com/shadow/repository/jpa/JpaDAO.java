package com.shadow.repository.jpa;

import com.shadow.entity.Command;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDAO extends JpaRepository<Command, String> {
}
