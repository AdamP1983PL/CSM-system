package com.example.csmSystem.service;

import com.example.csmSystem.dto.TestDto;
import com.example.csmSystem.entity.Test;
import com.example.csmSystem.mapper.TestMapper;
import com.example.csmSystem.repository.TestRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    private TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public TestDto createTest(TestDto testDto) {
        Test test = TestMapper.MAPPER.mapToTest(testDto);
        Test savedTest = testRepository.save(test);

        return TestMapper.MAPPER.mapToTestDto(savedTest);
    }

    @Override
    public TestDto getTestById(Long id) {
        Optional<Test> optionalTest = testRepository.findById(id);
        if(optionalTest.isPresent()){
            Test tempTest;
            tempTest = optionalTest.get();
            return TestMapper.MAPPER.mapToTestDto(tempTest);
        } else {
            throw new RuntimeException("There is no Test for id: " + id);
        }
    }

    @Override
    public List<TestDto> getAllTests() {
        List<Test> tests = testRepository.findAll();
        return tests.stream()
                .map(TestMapper.MAPPER::mapToTestDto)
                .collect(Collectors.toList());
    }

    @Override
    public TestDto updateTest(TestDto testDto) {
        Test existingTest = testRepository.findById(testDto.getId()).orElseThrow(
                ()-> new RuntimeException("There is no Test for id: " + testDto.getId())
        );
        existingTest.setName(testDto.getName());
        existingTest.setLocalDateTime(testDto.getLocalDateTime());
        Test updatedTest = testRepository.save(existingTest);
        return TestMapper.MAPPER.mapToTestDto(updatedTest);
    }

    @Override
    public void deleteTest(Long id) {
        Test existingTest = testRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("There is no Test for id: " + id)
        );
        testRepository.deleteById(id);
    }
}
