package com.api.bank.repository;

import com.api.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);

}
