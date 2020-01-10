package com.webstore.trill.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String name;
    private String password;

}
