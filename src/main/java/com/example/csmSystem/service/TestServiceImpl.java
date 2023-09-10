package com.example.csmSystem.service;

import com.example.csmSystem.model.entity.Test;
import com.example.csmSystem.model.repository.TestRepository;
import com.example.csmSystem.service.dto.TestDto;
import com.example.csmSystem.service.exceptions.NameAlreadyExistException;
import com.example.csmSystem.service.exceptions.ResourceNotFoundException;
import com.example.csmSystem.service.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public TestDto createTest(TestDto testDto) {
        log.info("Creating new Test {}, and saving it in the DB", testDto.getName());
        if (testRepository.findTestByName(testDto.getName()).isPresent())
            throw new NameAlreadyExistException("Name already exists in the DB");
        return TestMapper.MAPPER.mapToTestDto(testRepository.save(TestMapper.MAPPER.mapToTest(testDto)));
    }

    @Override
    public TestDto findTestById(UUID id) {
        log.info("Getting Test from the DB by id = {}", id);
        return TestMapper.MAPPER.mapToTestDto(testRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test", "id", id)));
    }

    @Override
    public List<TestDto> findAllTests() {
        log.info("Get all test from the DB");
        return testRepository.findAll().stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .toList();
    }

    @Override
    public TestDto updateTest(TestDto testDto) {
        log.info("Updating Test by test id = {}", testDto.getId());
        return TestMapper.MAPPER.mapToTestDto(testRepository.save(testRepository.findById(testDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Test", "id", testDto.getId()))));
    }

    @Override
    public TestDto deleteTest(UUID id) {
        log.info("Deleting Test by id = {} from the DB", id);
        Test test = testRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Test", "id", id));
        testRepository.deleteById(id);
        return TestMapper.MAPPER.mapToTestDto(test);
    }
}
