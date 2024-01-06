package com.example.csmSystem.controller;

import com.example.csmSystem.service.TestServiceImpl;
import com.example.csmSystem.service.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
    public TestDto findTestById(@PathVariable("id") UUID testId) {
        return testServiceImpl.findTestById(testId);
    }

    @Override
    public List<TestDto> findAllTests() {
        return testServiceImpl.findAllTests();
    }

    // todo updateTest without @PathVariable???
    @Override
    public TestDto updateTest(@RequestBody TestDto testDto) {
        return testServiceImpl.updateTest(testDto);
    }

    @Override
    public void deleteTestById(@PathVariable("id") UUID testId) {
        testServiceImpl.deleteTest(testId);
    }

    @Override
    public Page<TestDto> findAllTestsWithPagination(@PathVariable("pageNumber") int pageNumber,
                                                    @PathVariable("pageSize") int pageSize) {
        return testServiceImpl.findAllWithPagination(pageNumber, pageSize);
    }

    @Override
    public List<TestDto> findAndSortAllTestsAsc(Sort sort) {
        return testServiceImpl.sortAsc(sort);
    }

    @Override
    public List<TestDto> findAndSortAllTestsDesc(Sort sort) {
        return testServiceImpl.sortDesc(sort);
    }

    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    @Override
    public List<TestDto> findAndSortAllByOneFieldDynamically(@PathVariable("fieldName") String fieldName,
                                                             @PathVariable("sortDir") String sortDir) {
        return testServiceImpl.sortByOneFieldDynamically(fieldName, sortDir);
    }

    // todo @PathVariable or @RequestParam, above method
    /* @Override
        public List<TestDto> findAndSortAllByOneFieldDynamically(@RequestParam("fieldName") String fieldName,
                                                                 @RequestParam("sortDir") String sortDir) {
    return testServiceImpl.sortByOneFieldDynamically(fieldName, sortDir);
    }*/
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

    @Override
    public List<TestDto> findAndSortAllByTwoFieldsDynamically(@PathVariable("firstField") String firstField,
                                                              @PathVariable("secondField") String secondField,
                                                              @PathVariable("sortDir") String sortDir) {
        return testServiceImpl.sortByTwoFieldsDynamically(firstField, secondField, sortDir);
    }

    @Override
    public List<TestDto> findPaginateAndSortAllByOneFieldAsc(@PathVariable("pageNumber") int pageNumber,
                                                             @PathVariable("pageSize") int pageSize,
                                                             @PathVariable("fieldName") String fieldName) {
        return testServiceImpl.paginateAndSortByOneFieldAsc(pageNumber, pageSize, fieldName);
    }

    @Override
    public List<TestDto> findPaginateAndSortAllByOneFieldDesc(@PathVariable("pageNumber") int pageNumber,
                                                              @PathVariable("pageSize") int pageSize,
                                                              @PathVariable("fieldName") String fieldName) {
        return testServiceImpl.paginateAndSortByOneFieldDesc(pageNumber, pageSize, fieldName);
    }
}


//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
// todo @PathVariable or @RequestParam?????
    /*Use Case:
        Use @PathVariable when extracting values from the URI path.
        Use @RequestParam when extracting values from the query parameters.
    Syntax:
        @PathVariable values are specified directly in the URI path within curly braces.
        @RequestParam values are specified after the ? symbol in the URL.
    Example:
        @PathVariable: /users/{userId}
        @RequestParam: /users?role=admin
    * */
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx


