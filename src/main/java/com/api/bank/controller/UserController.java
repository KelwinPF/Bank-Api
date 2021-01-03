package com.api.bank.controller;

import com.api.bank.dto.UserDTO;
import com.api.bank.entity.User;
import com.api.bank.response.Response;
import com.api.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<Response<UserDTO>> create(@Valid @RequestBody UserDTO dto, BindingResult result) {

        Response<UserDTO> response = new Response<UserDTO>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if(service.existsByCpf(this.convertDtoToEntity(dto).getCpf())){
            response.getErrors().add("ja existe cpf cadastrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        };

        if(service.existsByEmail(this.convertDtoToEntity(dto).getEmail())){
            response.getErrors().add("ja existe email cadastrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        };

        User user = service.save(this.convertDtoToEntity(dto));
        response.setData(this.convertEntityToDto(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private User convertDtoToEntity(UserDTO dto) {
        User u = new User();
        u.setId(dto.getId());
        u.setEmail(dto.getEmail());
        u.setBirthday(dto.getBirthday());
        u.setCpf(dto.getCpf());
        u.setName(dto.getName());
        return u;
    }

    private UserDTO convertEntityToDto(User u) {
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setEmail(u.getEmail());
        dto.setName(u.getName());
        dto.setBirthday(u.getBirthday());
        dto.setCpf(u.getCpf());
        return dto;
    }
}
