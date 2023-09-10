package com.example.csmSystem.service;

import com.example.csmSystem.service.dto.TestDto;

import java.util.List;
import java.util.UUID;

public interface TestService {
    TestDto createTest(TestDto testDto);

    TestDto findTestById(UUID id);

    List<TestDto> findAllTests();

    TestDto updateTest(TestDto testDto);

    TestDto deleteTest(UUID id);
}
