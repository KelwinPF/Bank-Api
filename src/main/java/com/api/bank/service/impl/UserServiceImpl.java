package com.api.bank.service.impl;

import com.api.bank.entity.User;
import com.api.bank.repository.UserRepository;
import com.api.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Override
    public User save(User u) {
        repo.save(u);
        return u;
    }

    @Override
    public Boolean existsByCpf(String cpf) {
        return repo.existsByCpf(cpf);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

}
