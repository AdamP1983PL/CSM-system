package com.example.csmSystem.repository;

import com.example.csmSystem.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {

    Optional<Test> findTestByName(String name);
}
