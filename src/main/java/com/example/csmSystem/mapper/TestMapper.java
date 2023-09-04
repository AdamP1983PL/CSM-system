package com.example.csmSystem.mapper;

import com.example.csmSystem.dto.TestDto;
import com.example.csmSystem.entity.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TestMapper {

    /*notes for myself:
    * we don't have to implements these methods, MapStruct will create a code to implement these
    * methods at a compilation time,
    * TestMapper MAPPER... will provide the implementation for this interface at a compilation time
    */
    TestMapper MAPPER = Mappers.getMapper(TestMapper.class);


    /* both TestDto and Test should have identical fields names
     * if fields names are different we must use @Mapping annotation:
     * @Mapping(source = "sourceName(example: email)", target = "targetName(example: emailAddress)"
     */
    TestDto mapToTestDto(Test test);

    Test mapToTest(TestDto testDto);
}
