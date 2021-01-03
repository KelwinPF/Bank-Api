package com.api.bank.service;

import com.api.bank.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    @MockBean
    UserRepository repository;

    @Autowired
    UserService service;

    @Before
    public void setUp(){

        BDDMockito.given(repository.existsByCpf(Mockito.anyString())).willReturn(Boolean.TRUE);
        BDDMockito.given(repository.existsByEmail(Mockito.anyString())).willReturn(Boolean.TRUE);

    }

    @Test
    public void testFindByCpf(){
        Boolean user = service.existsByCpf("111.111.111-88");
        assertTrue(user);
    }

    @Test
    public void testFindByEmail(){
        Boolean email = service.existsByEmail("teste@email.com");
        assertTrue(email);
    }

}
