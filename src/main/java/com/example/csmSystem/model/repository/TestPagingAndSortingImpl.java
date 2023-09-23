package com.example.csmSystem.model.repository;

import com.example.csmSystem.model.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestPagingAndSortingImpl implements TestPaginationAndSorting {

    private final TestRepository testRepository;

    public TestPagingAndSortingImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public Page<Test> findAll(Pageable pageable, int pageNumber, int pageSize) {
        return testRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public List<Test> sortByOneFieldAsc(Sort sort) {
        return testRepository.findAll(Sort.by(Sort.Direction.ASC));
    }

    @Override
    public List<Test> sortByOneFieldDesc(Sort sort) {
        return testRepository.findAll(Sort.by(Sort.Direction.DESC));
    }

/*  Assuming that sort direction is coming from the Client
*   sortDir = "asc" || sortDir="desc"*/
    @Override
    public List<Test> sortByOneFieldDynamically(Sort sort, String fieldName, String sortDir) {

        sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(fieldName).ascending() : Sort.by(fieldName).descending();

        return testRepository.findAll(sort);
    }

    /*  Assuming that sort direction is coming from the Client
     *   sortDir = "asc" || sortDir="desc"*/
    @Override
    public List<Test> sortByTwoFields(Sort sort, String firstFieldName,
                                      String secondFieldName, String sortDir) {

        Sort sortByFirstField = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(firstFieldName).ascending() : Sort.by(firstFieldName).descending();

        Sort sortBySecondField = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(secondFieldName).ascending() : Sort.by(secondFieldName).descending();

        Sort sortByFirstThenSecondField = sortByFirstField.and(sortBySecondField);

        return testRepository.findAll(sortByFirstThenSecondField);
    }

    @Override
    public List<Test> paginateAndSortByOneFieldAsc(int pageNumber, int pageSize, Sort sort) {

        sort = Sort.by(Sort.Direction.ASC);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Test> page = testRepository.findAll(pageable);

        return page.getContent();
    }

    @Override
    public List<Test> paginateAndSortByOneFieldDesc(int pageNumber, int pageSize, Sort sort) {

        sort = Sort.by(Sort.Direction.DESC);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Test> page = testRepository.findAll(pageable);

        return page.getContent();
    }
}





























