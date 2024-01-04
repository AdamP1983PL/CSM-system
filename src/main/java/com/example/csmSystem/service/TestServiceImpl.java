package com.example.csmSystem.service;

import com.example.csmSystem.model.entity.TestClass;
import com.example.csmSystem.model.repository.TestClassRepository;
import com.example.csmSystem.service.dto.TestDto;
import com.example.csmSystem.service.exceptions.NameAlreadyExistException;
import com.example.csmSystem.service.exceptions.ResourceNotFoundException;
import com.example.csmSystem.service.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestClassRepository testClassRepository;

    @Override
    public TestDto createTest(TestDto testDto) {
        log.info("Creating new Test {}, and saving it in the DB", testDto.getName());

        if (testClassRepository.findTestByName(testDto.getName()).isPresent())
            throw new NameAlreadyExistException("Name already exists in the DB");

        return TestMapper.MAPPER.mapToTestDto(testClassRepository.save(TestMapper.MAPPER.mapToTest(testDto)));
    }

    @Override
    public TestDto findTestById(UUID id) {
        log.info("Finding Test from the DB by id = {}", id);

        return TestMapper.MAPPER.mapToTestDto(testClassRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test", "id", id)));
    }

    @Override
    public List<TestDto> findAllTests() {
        log.info("Find all test from the DB");

        return testClassRepository.findAll().stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .collect(Collectors.toList());
    }

    @Override
    public TestDto updateTest(TestDto testDto) {
        log.info("Updating Test by test id = {}", testDto.getId());

        return TestMapper.MAPPER.mapToTestDto(testClassRepository.save(testClassRepository.findById(testDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Test", "id", testDto.getId()))));
    }

    @Override
    public TestDto deleteTest(UUID id) {
        log.info("Deleting Test by id = {} from the DB", id);

        TestClass testClass = testClassRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test", "id", id));
        testClassRepository.deleteById(id);

        return TestMapper.MAPPER.mapToTestDto(testClass);
    }

    @Override
    public Page<TestDto> findAll(int pageNumber, int pageSize) {
        log.info("Find all test with Pageable: page number {}, page size {}", pageNumber, pageSize);

        Page<TestClass> pageResult = testClassRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<TestDto> testDtoList = pageResult.getContent().stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .collect(Collectors.toList());

        return new PageImpl<>(testDtoList, PageRequest.of(pageNumber, pageSize), pageResult.getTotalElements());
    }

    @Override
    public List<TestDto> sortByOneFieldAsc(Sort sort) {
        log.info("Find all tests ASC using Sort");

        return testClassRepository.findAll(Sort.by(Sort.Direction.ASC)).stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TestDto> sortByOneFieldDesc(Sort sort) {
        log.info("Find all tests DESC using Sort");

        return testClassRepository.findAll(Sort.by(Sort.Direction.DESC)).stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .collect(Collectors.toList());
    }

    /*  Assuming that sort direction is coming from the Client
     *   sortDir = "asc" || sortDir="desc"*/
    @Override
    public List<TestDto> sortByOneFieldDynamically(Sort sort, String fieldName, String sortDir) {
        log.info("Find all tests using Sort by one field dynamically");

        sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(fieldName).ascending() : Sort.by(fieldName).descending();

        return testClassRepository.findAll(sort).stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .collect(Collectors.toList());
    }

    /*  Assuming that sort direction is coming from the Client
     *   sortDir = "asc" || sortDir="desc"*/
    @Override
    public List<TestDto> sortByTwoFields(String firstField, String secondField, String sortDir) {
        log.info("Find all test using Sort by two fields dynamically");

        Sort sortByFirstField = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(firstField).ascending() : Sort.by(firstField).descending();
        Sort sortBySecondField = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(secondField).ascending() : Sort.by(secondField).descending();
        Sort sortByFirstThenSecondField = sortByFirstField.and(sortBySecondField);

        return testClassRepository.findAll(sortByFirstThenSecondField).stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TestDto> paginateAndSortByOneFieldAsc(int pageNumber, int pageSize, String fieldName) {
        log.info("Find all test using Sort by one field ASC, page number {} and page size {}", pageNumber, pageSize);

        Sort sort = Sort.by(fieldName).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<TestClass> pageResult = testClassRepository.findAll(pageable);

        return pageResult.getContent().stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TestDto> paginateAndSortByOneFieldDesc(int pageNumber, int pageSize, String fieldName) {
        log.info("Find all test using Sort by one field DESC, page number {}, page size {}", pageNumber, pageSize);

        Sort sort = Sort.by(fieldName).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<TestClass> pageResult = testClassRepository.findAll(pageable);

        return pageResult.getContent().stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .collect(Collectors.toList());
    }
}
