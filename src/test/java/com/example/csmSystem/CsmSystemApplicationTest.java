package com.example.csmSystem;

import com.example.csmSystem.model.entity.TestClass;
import com.example.csmSystem.model.repository.TestClassRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CsmSystemApplicationTest {

    @Autowired
    private TestClassRepository testClassRepository;

    @Test
    public void checkNumberOfElements() {
        // given
        TestClass testClass = new TestClass();

        // when
        testClassRepository.save(testClass);
        int size = testClassRepository.findAll().size();

        // then
        Assert.assertEquals(1, size);
    }

}
