package com.example.csmSystem.service;

import com.example.csmSystem.service.dto.TestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface TestService {
    TestDto createTest(TestDto testDto);

    TestDto findTestById(UUID id);

    List<TestDto> findAllTests();

    TestDto updateTest(TestDto testDto);

    void deleteTest(UUID id);

    Page<TestDto> findAllWithPagination(int pageNumber, int pageSize);

    List<TestDto> sortAsc(Sort sort);

    List<TestDto> sortDesc(Sort sort);

    List<TestDto> sortByOneFieldDynamically(String fieldName, String sortDir);

    List<TestDto> sortByTwoFieldsDynamically(String firstField, String secondField, String sortDir);

    List<TestDto> paginateAndSortByOneFieldAsc(int pageNumber, int pageSize,  String fieldName);

    List<TestDto> paginateAndSortByOneFieldDesc(int pageNumber, int pageSize, String fieldName);
}
