package com.example.csmSystem.service;

import com.example.csmSystem.dto.TestDto;
import com.example.csmSystem.entity.Test;
import com.example.csmSystem.exceptions.NameAlreadyExistException;
import com.example.csmSystem.exceptions.ResourceNotFoundException;
import com.example.csmSystem.mapper.TestMapper;
import com.example.csmSystem.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    private TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public TestDto createTest(TestDto testDto) {
        log.info("Creating new Test {}, and saving it in the DB", testDto.getName());

        Optional<Test> optionalTest = testRepository.findTestByName(testDto.getName());

        if (optionalTest.isPresent()) {
            throw new NameAlreadyExistException("Name already exists in the DB");
        } else {
            Test test = TestMapper.MAPPER.mapToTest(testDto);
            Test savedTest = testRepository.save(test);
            return TestMapper.MAPPER.mapToTestDto(savedTest);
        }
    }

    @Override
    public TestDto getTestById(Long id) {
        log.info("Getting Test from the DB by id = {}", id);

        Test test = testRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test", "id", id)
        );
        return TestMapper.MAPPER.mapToTestDto(test);
    }

    @Override
    public List<TestDto> getAllTests() {
        log.info("Get all test from the DB");

        List<Test> tests = testRepository.findAll();
        return tests.stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .collect(Collectors.toList());
    }

    @Override
    public TestDto updateTest(TestDto testDto) {
        log.info("Updating Test by test id = {}", testDto.getId());

        Test existingTest = testRepository.findById(testDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Test", "id", testDto.getId())
        );
        existingTest.setName(testDto.getName());
        existingTest.setBusinessDate(testDto.getBusinessDate());
        Test updatedTest = testRepository.save(existingTest);
        return TestMapper.MAPPER.mapToTestDto(updatedTest);
    }

    @Override
    public void deleteTest(Long id) {
        log.info("Deleting Test by id = {} from the DB", id);

        Test existingTest = testRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test", "id", id)
        );
        testRepository.deleteById(id);
    }
}
