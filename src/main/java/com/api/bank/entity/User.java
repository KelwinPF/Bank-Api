package com.api.bank.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="users")
public class User implements Serializable {

    private static final long serialVersionUID=1693850165739564098L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String cpf;
    @Column(nullable = false)
    private Date birthday;
    @Column(nullable = false,unique = true)
    private String email;
}
