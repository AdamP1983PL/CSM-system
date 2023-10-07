package com.example.csmSystem.service;

import com.example.csmSystem.model.entity.TestClass;
import com.example.csmSystem.model.repository.TestClassRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestClassServiceImpl implements TestClassService {

//    private final TestClassRepository testClassRepository;
//
//    public TestClassServiceImpl(TestClassRepository testClassRepository) {
//        this.testClassRepository = testClassRepository;
//    }
//
//    @Override
//    public Page<TestClass> findAll(Pageable pageable, int pageNumber, int pageSize) {
//        return testClassRepository.findAll(PageRequest.of(pageNumber, pageSize));
//    }
//
//    @Override
//    public List<TestClass> sortByOneFieldAsc(Sort sort) {
//        return testClassRepository.findAll(Sort.by(Sort.Direction.ASC));
//    }
//
//    @Override
//    public List<TestClass> sortByOneFieldDesc(Sort sort) {
//        return testClassRepository.findAll(Sort.by(Sort.Direction.DESC));
//    }
//
//    /*  Assuming that sort direction is coming from the Client
//     *   sortDir = "asc" || sortDir="desc"*/
//    @Override
//    public List<TestClass> sortByOneFieldDynamically(Sort sort, String fieldName, String sortDir) {
//
//        sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
//                ? Sort.by(fieldName).ascending() : Sort.by(fieldName).descending();
//
//        return testClassRepository.findAll(sort);
//    }
//
//    /*  Assuming that sort direction is coming from the Client
//     *   sortDir = "asc" || sortDir="desc"*/
//    @Override
//    public List<TestClass> sortByTwoFields(Sort sort, String firstFieldName,
//                                           String secondFieldName, String sortDir) {
//
//        Sort sortByFirstField = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
//                ? Sort.by(firstFieldName).ascending() : Sort.by(firstFieldName).descending();
//
//        Sort sortBySecondField = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
//                ? Sort.by(secondFieldName).ascending() : Sort.by(secondFieldName).descending();
//
//        Sort sortByFirstThenSecondField = sortByFirstField.and(sortBySecondField);
//
//        return testClassRepository.findAll(sortByFirstThenSecondField);
//    }
//
//    @Override
//    public List<TestClass> paginateAndSortByOneFieldAsc(int pageNumber, int pageSize, Sort sort) {
//
//        sort = Sort.by(Sort.Direction.ASC);
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
//        Page<TestClass> page = testClassRepository.findAll(pageable);
//
//        return page.getContent();
//    }
//
//    @Override
//    public List<TestClass> paginateAndSortByOneFieldDesc(int pageNumber, int pageSize, Sort sort) {
//
//        sort = Sort.by(Sort.Direction.DESC);
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
//        Page<TestClass> page = testClassRepository.findAll(pageable);
//
//        return page.getContent();
//    }
}





























