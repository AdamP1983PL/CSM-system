package com.example.csmSystem.controller;

import com.example.csmSystem.service.dto.TestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Find \"Test\" by id REST API",
            description = "Find \"Test\" REST API is used to find \"Test\" from the DB by id")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TestDto findTestById(@PathVariable("id") UUID testId);

    @Operation(summary = "Find all \"Tests\" REST API",
            description = "Find all \"Tests\" REST API is used to find all the \"Tests\" from the DB")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @GetMapping("/allTests")
    @ResponseStatus(HttpStatus.OK)
    List<TestDto> findAllTests();

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
    void deleteTestById(@PathVariable("id") UUID testId);

    @Operation(summary = "Find all \"Tests\" REST API with Pagination",
            description = "Find all \"Tests\" REST API is used to find all the \"Tests\" from the DB with Pagination")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @GetMapping("/paginated/{pageNumber}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    Page<TestDto> findAllTestsWithPagination(@PathVariable("pageNumber") int pageNumber,
                                             @PathVariable("pageSize") int pageSize);

    @Operation(summary = "Sort all \"Tests\" REST API with Sorting ASC",
            description = "Sort all \"Tests\" REST API is used to sort ASC all the \"Tests\" from the DB")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @GetMapping("/sort/asc")
    @ResponseStatus(HttpStatus.OK)
    List<TestDto> findAndSortAllTestsAsc(Sort sort);

    @Operation(summary = "Sort all \"Tests\" REST API with Sorting DESC",
            description = "Sort all \"Tests\" REST API is used to sort DESC all the \"Tests\" from the DB")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @GetMapping("/sort/desc")
    @ResponseStatus(HttpStatus.OK)
    List<TestDto> findAndSortAllTestsDesc(Sort sort);

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    @Operation(summary = "Sort all \"Tests\" REST API with Sorting by given field, given direction",
            description = "Sort all \"Tests\" REST API is used to sort data by given field in given direction")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @GetMapping("sort-by-field-by-direction/{fieldName}/{sortDir}")
    @ResponseStatus(HttpStatus.OK)
    List<TestDto> findAndSortAllByOneFieldDynamically(@PathVariable("fieldName") String fieldName,
                                                      @PathVariable("sortDir") String sortDir);

    // todo @PathVariable or @RequestParam,
    /*List<TestDto> findAndSortAllByOneFieldDynamically(@RequestParam("fieldName") String fieldName,
                                                        @RequestParam("sortDir") String sortDir);
    * */
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx


    @Operation(summary = "Sort all \"Tests\" REST API with Sorting by given two fields, given direction",
            description = "Sort all \"Tests\" REST API is used to sort data by given two fields in given direction")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @GetMapping("sort-by-two-fields-by-direction/{firstField}/{secondField}/{sortDir}")
    @ResponseStatus(HttpStatus.OK)
    List<TestDto> findAndSortAllByTwoFieldsDynamically(@PathVariable("firstField") String firstField,
                                                       @PathVariable("secondField") String secondField,
                                                       @PathVariable("sortDir") String sortDir);

    @Operation(summary = "Paginate and sort all \"Tests\" REST API with Sorting by given field in ascending order",
            description = "Paginate and sort all \"Tests\" REST API is used to sort data by given field in ascending order")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @GetMapping("paginate-sort-by-one-fields-asc/{pageNumber}/{pageSize}/{fieldName}")
    @ResponseStatus(HttpStatus.OK)
    List<TestDto> findPaginateAndSortAllByOneFieldAsc(@PathVariable("pageNumber") int pageNumber,
                                                      @PathVariable("pageSize") int pageSize,
                                                      @PathVariable("fieldName") String fieldName);

    @Operation(summary = "Paginate and sort all \"Tests\" REST API with Sorting by given field in descending order",
            description = "Paginate and sort all \"Tests\" REST API is used to sort data by given field in descending order")
    @ApiResponse(responseCode = "200", description = "Http Status 200 OK")
    @GetMapping("paginate-sort-by-one-fields-desc/{pageNumber}/{pageSize}/{fieldName}")
    @ResponseStatus(HttpStatus.OK)
    List<TestDto> findPaginateAndSortAllByOneFieldDesc(@PathVariable("pageNumber") int pageNumber,
                                                       @PathVariable("pageSize") int pageSize,
                                                       @PathVariable("fieldName") String fieldName);


}


