package com.example.csmSystem.service;

import com.example.csmSystem.model.entity.TestClass;
import com.example.csmSystem.model.repository.TestClassRepository;
import com.example.csmSystem.service.dto.TestDto;
import com.example.csmSystem.service.exceptions.ResourceNotFoundException;
import com.example.csmSystem.service.mapper.TestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    @Mock
    private TestClassRepository testClassRepository;

    @Mock
    private TestMapper testMapper;

    @InjectMocks
    private TestServiceImpl testServiceImpl;

    TestClass testClass1;
    TestClass testClass2;
    TestDto testClassDto1;
    TestDto testClassDto2;

    @BeforeEach
    public void initialise() {
        testClass1 = TestClass.builder()
                .uuid(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .name("test1")
                .businessDate(LocalDateTime.of(2024, 1, 1, 10, 0, 0))
                .build();

        testClass2 = TestClass.builder()
                .name("test2")
                .businessDate(LocalDateTime.of(2000, 1, 1, 10, 0, 0))
                .build();

        testClassDto1 = TestDto.builder()
                .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .name("testDto1")
                .businessDate(LocalDateTime.of(2024, 1, 1, 10, 0, 0))
                .build();

        testClassDto2 = TestDto.builder()
                .name("testDto2")
                .businessDate(LocalDateTime.of(2000, 1, 1, 10, 0, 0))
                .build();
    }

    @Test
    @DisplayName("Test createTest(TestDto testDto)")
    public void givenTestClassObject_whenCreateTest_thenReturnTestClassObject() {
        // given
        given(testClassRepository.findByName(testClassDto1.getName())).willReturn(Optional.empty());
        given(testClassRepository.save(testClass1)).willReturn(testClass1);
        given(testMapper.mapToTest(testClassDto1)).willReturn(testClass1);
        given(testMapper.mapToTestDto(testClass1)).willReturn(testClassDto1);

//        // when  todo WTF????
//        Test savedTestClass = testServiceImpl.createTest(testClassDto1);
//
//        // then
//        assertNotNull(savedTestClass);
//        assertEquals("test1", savedTestClass.getName());

    }

    @Test
    @DisplayName("Test findTestById(UUID id)")
    public void givenTestClassId_whenFindTestById_thenReturnTestClassObject() {
        // given
        given(testClassRepository.findById(testClass1.getUuid())).willReturn(Optional.ofNullable(testClass1));

        // when
        TestDto testdto = testServiceImpl.findTestById(testClass1.getUuid());

        // then
        assertNotNull(testdto);
        assertEquals("test1", testdto.getName());
    }

    @Test
    @DisplayName("Test FindTestById(UUID id) should throw ResourceNotFoundException")
    public void givenTestClassId_whenFindTestById_thenThrowException() {
        // given
        given(testClassRepository.findById(testClass1.getUuid())).willReturn(Optional.empty());

        // when, then
        assertThrows(ResourceNotFoundException.class, () -> {
            testServiceImpl.findTestById(testClass1.getUuid());
        });
        verify(testClassRepository, never()).findAll();
    }

    @Test
    @DisplayName("Test findAllTests()")
    public void givenListOfObjects_whenFindAllTests_thenReturnListOfObjects() {
        // given
        given(testClassRepository.findAll()).willReturn(List.of(testClass1, testClass2));

        // when
        List<TestDto> testDtoList = testServiceImpl.findAllTests();

        // then
        assertNotNull(testDtoList);
        assertEquals(2, testDtoList.size());
    }

    @Test
    @DisplayName("Test updateTest(TestDto testDto)")
    public void givenTestClassDtoObject_whenUpdate_thenReturnUpdatedTestClassDtoObject() {
        // given
        given(testClassRepository.findById(testClass1.getUuid())).willReturn(Optional.ofNullable(testClass1));
        given(testClassRepository.save(testClass1)).willReturn(testClass1);


        // when
        testClass1.setName("updated name");
        testClass1.setBusinessDate(LocalDateTime.now());
        TestDto updatedTestClassDto = testServiceImpl.updateTest(testClassDto1);

        // then
        assertEquals("updated name", testClass1.getName());
    }

    @Test
    @DisplayName("Test deleteTest(UUID id)")
    public void givenListOfOneTest_whenDeleteTest_returnEmptyList() {
        // given
        List<TestDto> testDtoList = List.of(testClassDto1, testClassDto2);
        given(testClassRepository.findById(testClass1.getUuid())).willReturn(Optional.ofNullable(testClass1));

        // when
        testServiceImpl.deleteTest(testClass1.getUuid());
        List<TestDto> remainingList = testServiceImpl.findAllTests();

        // then
        assertEquals(0, remainingList.size());
    }

    // todo writing tests for pagination and sorting???


}


































