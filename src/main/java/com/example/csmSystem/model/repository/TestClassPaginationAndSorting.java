package com.example.csmSystem.model.repository;

import com.example.csmSystem.model.entity.TestClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TestClassPaginationAndSorting {


    Page<TestClass> findAll(Pageable pageable, int pageNumber, int pageSize);

    List<TestClass> sortByOneFieldAsc(Sort sort);

    List<TestClass> sortByOneFieldDesc(Sort sort);

    List<TestClass> sortByOneFieldDynamically(Sort sort, String fieldName, String sortDir);

    List<TestClass> sortByTwoFields(Sort sort, String firstFieldName,
                                    String secondFieldName, String sortDir);

    List<TestClass> paginateAndSortByOneFieldAsc(int pageNumber, int pageSize, Sort sort);

    List<TestClass> paginateAndSortByOneFieldDesc(int pageNumber, int pageSize, Sort sort);
}
