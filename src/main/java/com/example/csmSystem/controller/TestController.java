package com.example.csmSystem.controller;

import com.example.csmSystem.dto.TestDto;
import com.example.csmSystem.service.TestServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUDE REST API for \"Test\" Resource",
        description = "Create \"Test\", Update \"Test\", Get \"Test\", Get All \"Tests\", Delete \"Test\""
)
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestServiceImpl testServiceImpl;

    public TestController(TestServiceImpl testServiceImpl) {
        this.testServiceImpl = testServiceImpl;
    }

    /*localDateTime hardcoded in POSTMAN!!!*/
    @Operation(
            summary = "Create \"Test\" REST API",
            description = "Create \"Test\" REST API is used to save newly created \"Test\" in the DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<TestDto> createTest(@RequestBody TestDto testDto) {
        TestDto savedTest = testServiceImpl.createTest(testDto);
        return new ResponseEntity<>(savedTest, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get \"Test\" by id REST API",
            description = "Get \"Test\" REST API is used to get \"Test\" from the DB by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable("id") Long testId) {
        TestDto testDto = testServiceImpl.getTestById(testId);
        return new ResponseEntity<>(testDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Get all \"Tests\" REST API",
            description = "Get all \"Tests\" REST API is used to get all the \"Tests\" from the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/allTests")
    public ResponseEntity<List<TestDto>> getAllTests() {
        List<TestDto> allTests = testServiceImpl.getAllTests();
        return new ResponseEntity<>(allTests, HttpStatus.OK);
    }

    @Operation(
            summary = "Update \"Test\" REST API",
            description = "Update \"Test\" REST API is used to update particular \"Test\" in the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping("/{id}")
    public ResponseEntity<TestDto> updateTest(@PathVariable("id") Long testId,
                                              @RequestBody TestDto testDto) {
        testDto.setId(testId);
        TestDto updatedTestDto = testServiceImpl.updateTest(testDto);
        return new ResponseEntity<>(updatedTestDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete \"Test\" REST API",
            description = "Delete \"Test\" REST API is used to delete particular \"Test\" from the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestById(@PathVariable("id") Long testId) {
        testServiceImpl.deleteTest(testId);
        return new ResponseEntity<>("Test deleted", HttpStatus.OK);
    }
}

