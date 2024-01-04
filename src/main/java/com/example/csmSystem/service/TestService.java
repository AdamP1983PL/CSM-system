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

    TestDto deleteTest(UUID id);

    Page<TestDto> findAllWithPagination(int pageNumber, int pageSize);

    List<TestDto> sortByOneFieldAsc(Sort sort);

    List<TestDto> sortByOneFieldDesc(Sort sort);

    List<TestDto> sortByOneFieldDynamically(Sort sort, String fieldName, String sortDir);

    List<TestDto> sortByTwoFields(String firstField, String secondField, String sortDir);

    List<TestDto> paginateAndSortByOneFieldAsc(int pageNumber, int pageSize,  String fieldName);

    List<TestDto> paginateAndSortByOneFieldDesc(int pageNumber, int pageSize, String fieldName);
}
