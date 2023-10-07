package com.example.csmSystem.service;

import com.example.csmSystem.model.entity.TestClass;
import com.example.csmSystem.model.repository.TestClassRepository;
import com.example.csmSystem.service.dto.TestDto;
import com.example.csmSystem.service.exceptions.NameAlreadyExistException;
import com.example.csmSystem.service.exceptions.ResourceNotFoundException;
import com.example.csmSystem.service.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


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
        log.info("Getting Test from the DB by id = {}", id);
        return TestMapper.MAPPER.mapToTestDto(testClassRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test", "id", id)));
    }

    @Override
    public List<TestDto> findAllTests() {
        log.info("Get all test from the DB");
        return testClassRepository.findAll().stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .toList();
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
    public Page<TestClass> findAll(Pageable pageable, int pageNumber, int pageSize) {
        return testClassRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public List<TestClass> sortByOneFieldAsc(Sort sort) {
        return testClassRepository.findAll(Sort.by(Sort.Direction.ASC));
    }

    @Override
    public List<TestClass> sortByOneFieldDesc(Sort sort) {
        return testClassRepository.findAll(Sort.by(Sort.Direction.DESC));
    }

    /*  Assuming that sort direction is coming from the Client
     *   sortDir = "asc" || sortDir="desc"*/
    @Override
    public List<TestClass> sortByOneFieldDynamically(Sort sort, String fieldName, String sortDir) {

        sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(fieldName).ascending() : Sort.by(fieldName).descending();

        return testClassRepository.findAll(sort);
    }

    /*  Assuming that sort direction is coming from the Client
     *   sortDir = "asc" || sortDir="desc"*/
    @Override
    public List<TestClass> sortByTwoFields(Sort sort, String firstFieldName,
                                           String secondFieldName, String sortDir) {

        Sort sortByFirstField = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(firstFieldName).ascending() : Sort.by(firstFieldName).descending();

        Sort sortBySecondField = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(secondFieldName).ascending() : Sort.by(secondFieldName).descending();

        Sort sortByFirstThenSecondField = sortByFirstField.and(sortBySecondField);

        return testClassRepository.findAll(sortByFirstThenSecondField);
    }

    @Override
    public List<TestClass> paginateAndSortByOneFieldAsc(int pageNumber, int pageSize, Sort sort) {

        sort = Sort.by(Sort.Direction.ASC);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<TestClass> page = testClassRepository.findAll(pageable);

        return page.getContent();
    }

    @Override
    public List<TestClass> paginateAndSortByOneFieldDesc(int pageNumber, int pageSize, Sort sort) {

        sort = Sort.by(Sort.Direction.DESC);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<TestClass> page = testClassRepository.findAll(pageable);

        return page.getContent();
    }


}
