package com.webstore.trill.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SellEntity {
    @Id
    private long id;

    private double price;
    //Items

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;


}
