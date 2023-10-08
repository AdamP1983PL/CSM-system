package com.example.csmSystem.controller;

import com.example.csmSystem.service.TestServiceImpl;
import com.example.csmSystem.service.dto.TestDto;
import lombok.RequiredArgsConstructor;
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

    public ResponseEntity<TestDto> createTest(@RequestBody TestDto testDto) {
        return new ResponseEntity<>(testServiceImpl.createTest(testDto), HttpStatus.CREATED);
    }

    public ResponseEntity<TestDto> getTestById(@PathVariable("id") UUID testId) {
        return new ResponseEntity<>(testServiceImpl.findTestById(testId), HttpStatus.OK);
    }

    public ResponseEntity<List<TestDto>> getAllTests() {
        return new ResponseEntity<>(testServiceImpl.findAllTests(), HttpStatus.OK);
    }

    public ResponseEntity<TestDto> updateTest(@RequestBody TestDto testDto) {
        return new ResponseEntity<>(testServiceImpl.updateTest(testDto), HttpStatus.OK);
    }

    public ResponseEntity<TestDto> deleteTestById(@PathVariable("id") UUID testId) {
        return new ResponseEntity<>(testServiceImpl.deleteTest(testId), HttpStatus.OK);
    }

//    public ResponseEntity<Page<TestDto>> findAllTests(
//            @RequestParam(name = "pageNumber", defaultValue = "0")
//    )

    // ...
}

