package com.example.csmSystem.model.repository;

import com.example.csmSystem.model.entity.TestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TestClassRepositoryTest {

    @Autowired
    TestClassRepository testClassRepository;

    TestClass testClass1;
    TestClass testClass2;

    @BeforeEach
    public void initialize() {
        testClass1 = TestClass.builder()
                .name("name 1")
                .businessDate(LocalDateTime.now())
                .build();
        testClass2 = TestClass.builder()
                .name("name 2")
                .businessDate(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("JUnit test for save() method")
    public void testSaveTestClass() {
        // given
        // when
        TestClass savedTestClass = testClassRepository.save(testClass1);

        // then
        assertNotNull(savedTestClass);
        assertEquals("name 1", savedTestClass.getName());
        assertEquals(2024, savedTestClass.getBusinessDate().getYear());
    }

}
