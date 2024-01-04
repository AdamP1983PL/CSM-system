package com.example.csmSystem.controller;

import com.example.csmSystem.service.TestServiceImpl;
import com.example.csmSystem.service.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestController implements TestControllerApi {
    private final TestServiceImpl testServiceImpl;

    @Override
    public TestDto createTest(@RequestBody TestDto testDto) {
        return testServiceImpl.createTest(testDto);
    }

    @Override
    public TestDto getTestById(@PathVariable("id") UUID testId) {
        return testServiceImpl.findTestById(testId);
    }

    @Override
    public List<TestDto> getAllTests() {
        return testServiceImpl.findAllTests();
    }

    // todo updateTest without @PathVariable???
    @Override
    public TestDto updateTest(@RequestBody TestDto testDto) {
        return testServiceImpl.updateTest(testDto);
    }

    @Override
    public TestDto deleteTestById(@PathVariable("id") UUID testId) {
        return testServiceImpl.deleteTest(testId);
    }




}

