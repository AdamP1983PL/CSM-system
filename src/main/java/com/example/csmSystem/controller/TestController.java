package com.example.csmSystem.controller;

import com.example.csmSystem.dto.TestDto;
import com.example.csmSystem.exceptions.ErrorDetails;
import com.example.csmSystem.exceptions.ResourceNotFoundException;
import com.example.csmSystem.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    /*localDateTime hardcoded in POSTMAN!!!*/
    @PostMapping("/create")
    public ResponseEntity<TestDto> createTest(@RequestBody TestDto testDto) {
        TestDto savedTest = testService.createTest(testDto);
        return new ResponseEntity<>(savedTest, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable("id") Long testId) {
        TestDto testDto = testService.getTestById(testId);
        return new ResponseEntity<>(testDto, HttpStatus.FOUND);
    }

    @GetMapping("/allTests")
    public ResponseEntity<List<TestDto>> getAllTests() {
        List<TestDto> allTests = testService.getAllTests();
        return new ResponseEntity<>(allTests, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestDto> updateTest(@PathVariable("id") Long testId,
                                              @RequestBody TestDto testDto) {
        testDto.setId(testId);
        TestDto updatedTestDto = testService.updateTest(testDto);
        return new ResponseEntity<>(updatedTestDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestById(@PathVariable("id") Long testId) {
        testService.deleteTest(testId);
        return new ResponseEntity<>("Test deleted", HttpStatus.OK);
    }
}
