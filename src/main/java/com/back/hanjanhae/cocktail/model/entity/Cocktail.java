package com.back.hanjanhae.cocktail.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cocktail")
public class Cocktail {

    @Id
    @Column(name = "cocktail_id")
    private Long cocktailId;

    @Column(name = "cocktail_name")
    private String cocktailName;

    @Column(name = "cocktail_how")
    private String cocktailHow;

    @Column(name = "cocktail_picture")
    private String cocktailPicture;
}
