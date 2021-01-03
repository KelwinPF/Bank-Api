package com.api.bank.service;

import com.api.bank.entity.User;

public interface UserService {

    User save(User u);
    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);
}
