package com.api.bank.repository;

import com.api.bank.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    private static final String cpf = "111.111.111-88";
    private static final String email = "fulano@email.com";

    @Autowired
    UserRepository repository;

    @Before
    public void setUp(){
        User u = new User();
        u.setName("fulano beltrano");
        u.setCpf(cpf);
        u.setEmail(email);
        u.setBirthday(new Date());
        repository.save(u);
    }

    @After
    public void tearDown(){
        repository.deleteAll();
    }

    @Test
    public void testSave(){
        User u = new User();
        u.setName("fulano beltrano 2");
        u.setCpf("111.111.111-99");
        u.setEmail("fulano1@email.com");
        u.setBirthday(new Date());

        User response = repository.save(u);
        assertNotNull(response);
    }

    @Test
    public void existsByCpf(){
        Boolean response = repository.existsByCpf(cpf);

        assertTrue(response);
    }

    @Test
    public void existsByEmail(){
        Boolean response = repository.existsByEmail(email);

        assertTrue(response);
    }
}
