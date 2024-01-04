package com.example.csmSystem.model.repository;

import com.example.csmSystem.model.entity.TestClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TestClassRepository extends JpaRepository<TestClass, UUID> {

    Optional<TestClass> findTestByName(String name);

}
