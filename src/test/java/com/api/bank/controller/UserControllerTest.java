package com.api.bank.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.bank.dto.UserDTO;
import com.api.bank.entity.User;
import com.api.bank.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    private static final Long Id = 1L;
    private static final String EMAIL = "teste@email.com";
    private static final String NAME = "User test";
    private static final String URL = "/user";
    private static final String CPF= "811.807.880-95";
    private static final Date BIRTH = new Date();

    @MockBean
    UserService service;

    @Autowired
    MockMvc mvc;

    @Test
    public void testService() throws Exception {
        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
        mvc.perform(MockMvcRequestBuilders.post(URL)
                .content(getJsonPayload(Id,EMAIL,NAME,CPF,BIRTH))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(Id))
                .andExpect(jsonPath("$.data.email").value(EMAIL))
                .andExpect(jsonPath("$.data.name").value(NAME))
                .andExpect(jsonPath("$.data.cpf").value(CPF));
    }

    @Test
    public void testSaveInvalidUser() throws JsonProcessingException,Exception{
        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(Id, "emaixl", NAME,CPF,BIRTH))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Email inválido"));
    }

    public User getMockUser(){
        User u = new User();
        u.setId(Id);
        u.setEmail(EMAIL);
        u.setName(NAME);
        u.setBirthday(BIRTH);
        u.setCpf(CPF);
        return u;
    }

    public String getJsonPayload(Long id,String Email,String name,String cpf,Date birth) throws JsonProcessingException {
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setEmail(Email);
        dto.setName(name);
        dto.setBirthday(birth);
        dto.setCpf(cpf);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }
}
