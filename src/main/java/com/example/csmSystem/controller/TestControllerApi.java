package com.example.csmSystem.controller;

import com.example.csmSystem.service.dto.TestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "CRUDE REST API for \"Test\" Resource",
        description = "Create \"Test\", Update \"Test\", Get \"Test\", Get All \"Tests\", Delete \"Test\"")
@RequestMapping("/v1/api/test")
public interface TestControllerApi {

    @Operation(summary = "Create \"Test\" REST API",
            description = "Create \"Test\" REST API is used to save newly created \"Test\" in the DB")
    @ApiResponse(responseCode = "201", description = "Http Status 201 CREATED")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    TestDto createTest(@RequestBody TestDto testDto);

    @Operation(summary = "Get \"Test\" by id REST API",
            description = "Get \"Test\" REST API is used to get \"Test\" from the DB by id")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TestDto getTestById(@PathVariable("id") UUID testId);

    @Operation(summary = "Get all \"Tests\" REST API",
            description = "Get all \"Tests\" REST API is used to get all the \"Tests\" from the DB")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @GetMapping("/allTests")
    @ResponseStatus(HttpStatus.OK)
    List<TestDto> getAllTests();

    @Operation(summary = "Update \"Test\" REST API",
            description = "Update \"Test\" REST API is used to update particular \"Test\" in the DB")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    TestDto updateTest(@RequestBody TestDto testDto);

    @Operation(summary = "Delete \"Test\" REST API",
            description = "Delete \"Test\" REST API is used to delete particular \"Test\" from the DB")
    @ApiResponse(responseCode = "204", description = "Http Status 204 NO CONTENT")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    TestDto deleteTestById(@PathVariable("id") UUID testId);











}



























