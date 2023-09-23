package com.example.csmSystem.service.mapper;

import com.example.csmSystem.model.entity.TestClass;
import com.example.csmSystem.service.dto.TestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TestMapper {

    /*
     * we don't have to implements these methods, MapStruct will create a code to implement these
     * methods at a compilation time,
     * TestMapper MAPPER... will provide the implementation for this interface at a compilation time
     */
    TestMapper MAPPER = Mappers.getMapper(TestMapper.class);


    /* both TestDto and Test should have identical fields names
     * if fields names are different we must use @Mapping annotation:
     * @Mapping(source = "sourceName(example: email)", target = "targetName(example: emailAddress)"
     */
    TestDto mapToTestDto(TestClass testClass);

    TestClass mapToTest(TestDto testDto);
}
