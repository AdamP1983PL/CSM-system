package com.example.csmSystem.service;

import com.example.csmSystem.dto.TestDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestService {

    TestDto createTest(TestDto testDto);

    TestDto getTestById(Long id);

    List<TestDto> getAllTests();

    TestDto updateTest(TestDto testDto);

    void deleteTest (Long id);
}
