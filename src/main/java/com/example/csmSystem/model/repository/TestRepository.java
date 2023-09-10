package com.example.csmSystem.model.repository;

import com.example.csmSystem.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TestRepository extends JpaRepository<Test, UUID> {
    Optional<Test> findTestByName(String name);
}
