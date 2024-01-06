package com.example.csmSystem.model.repository;

import com.example.csmSystem.model.entity.TestClass;
import com.example.csmSystem.service.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TestClassRepositoryTestWithoutDataJpaTest {

    @Autowired
    TestClassRepository testClassRepository;

    TestClass testClass1;
    TestClass testClass2;

    @BeforeEach
    public void initialise() {
        testClass1 = TestClass.builder()
                .name("name 1")
                .businessDate(LocalDateTime.now())
                .build();
        log.info("\t\t====>>>>testClass1 created");
        testClass2 = TestClass.builder()
                .name("name 2")
                .businessDate(LocalDateTime.now())
                .build();
        log.info("\t\t====>>>>testClass2 created");
    }

    @AfterEach
    public void cleanUp() {
        testClassRepository.deleteAll();
        log.info("\t\t====>>>> database cleaned up");
    }

    @Test
    @DisplayName("JUnit test for save() method")
    public void testSave() {
        // given
        // when
        TestClass savedTestClass = testClassRepository.save(testClass1);

        // then
        assertNotNull(savedTestClass);
        assertEquals("name 1", savedTestClass.getName());
        assertEquals(2024, savedTestClass.getBusinessDate().getYear());
    }

    @Test
    @DisplayName("JUnit test for saveAll() method")
    public void testSaveAll() {
        // given
        // when
        List<TestClass> savedTests = testClassRepository.saveAll(List.of(testClass1, testClass2));

        // then
        assertEquals(2, savedTests.size());
        assertEquals("name 1", savedTests.get(0).getName());
        assertEquals(2024, savedTests.get(1).getBusinessDate().getYear());
    }

    @Test
    @DisplayName("JUnit test for findById() method")
    public void testFindById() {
        // given
        List<TestClass> savedTests = testClassRepository.saveAll(List.of(testClass1, testClass2));

        // when
        Optional<TestClass> optionalTestClass = testClassRepository.findById(testClass1.getUuid());

        // then
        assertEquals(2, savedTests.size());
        assertNotNull(optionalTestClass);
        assertEquals("name 1", optionalTestClass.get().getName());
    }

    @Test
    @DisplayName("JUnit test for findAll() method")
    public void testFindAll() {
        // given
        List<TestClass> savedTests = testClassRepository.saveAll(List.of(testClass1, testClass2));

        // when
        List<TestClass> existingTests = testClassRepository.findAll();

        // then
        assertEquals(2, existingTests.size());
        assertNotNull(existingTests.get(0));
        assertNotNull(existingTests.get(1));
    }

    @Test
    @DisplayName("JUnit test for deleteById() method")
    public void testDeleteById() {
        // given
        List<TestClass> savedTests = testClassRepository.saveAll(List.of(testClass1, testClass2));

        // when
        testClassRepository.deleteById(testClass1.getUuid());
        List<TestClass> remainingSavedTests = testClassRepository.findAll();

        // then
        assertEquals(1, remainingSavedTests.size());
        assertFalse(testClassRepository.findById(testClass1.getUuid()).isPresent());
    }

    @Test
    @DisplayName("JUnit test for findByName() query")
    public void testFindByName() {
        // given
        List<TestClass> savedTests = testClassRepository.saveAll(List.of(testClass1, testClass2));

        // when
        String name = "name 1";
        Optional<TestClass> savedTestClass = testClassRepository.findByName(name);

        // then
        assertTrue(savedTestClass.isPresent());
        assertEquals(2024, savedTestClass.get().getBusinessDate().getYear());

    }


}
