package com.webstore.trill.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @NotNull
    private String name;

    @NotNull
    private String main_image;

    @ElementCollection
    @CollectionTable(
            name = "size",
            joinColumns = @JoinColumn(name = "ITEM_ID")
    )
    @Column(name= "SIZE")
    private Set<String> sizes = new HashSet<String>();

    @NotNull
    private double price;

    @ElementCollection
    @CollectionTable(
            name = "CARACTERISTICS",
            joinColumns = @JoinColumn(name = "CARACTERISTICS_ID")
    )
    @Column(name= "CARACTERISTICS")
    private Set<String> caracteristics = new HashSet<String>();

    @ElementCollection
    @CollectionTable(
            name = "IMAGES",
            joinColumns = @JoinColumn(name = "IMAGES_ID")
    )
    @Column(name= "FILENAME")
    private Set<String> images = new HashSet<String>();

}
