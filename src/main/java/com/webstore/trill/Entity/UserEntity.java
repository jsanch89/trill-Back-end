package com.webstore.trill.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String name;
    private String password;

    @OneToMany(mappedBy = "user")
    @JoinColumn(
            name = "USER_ID",
            nullable = false
    )
    Collection<SellEntity> sells = new ArrayList<>();
}
