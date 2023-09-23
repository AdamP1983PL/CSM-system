package com.example.csmSystem.model.repository;

import com.example.csmSystem.model.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TestPaginationAndSorting {


    Page<Test> findAll(Pageable pageable, int pageNumber, int pageSize);

    List<Test> sortByOneFieldAsc(Sort sort);

    List<Test> sortByOneFieldDesc(Sort sort);

    List<Test> sortByOneFieldDynamically(Sort sort, String fieldName, String sortDir);

    List<Test> sortByTwoFields(Sort sort, String firstFieldName,
                               String secondFieldName, String sortDir);

    List<Test> paginateAndSortByOneFieldAsc(int pageNumber, int pageSize, Sort sort);

    List<Test> paginateAndSortByOneFieldDesc(int pageNumber, int pageSize, Sort sort);
}
