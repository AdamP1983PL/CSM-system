package com.example.csmSystem.repository;

import com.example.csmSystem.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
