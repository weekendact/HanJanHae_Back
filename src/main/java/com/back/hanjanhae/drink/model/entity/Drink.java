package com.back.hanjanhae.drink.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drink")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id")
    private Long drinkId;

    @Column(name = "drink_type")
    private Integer drinkType;

    @Column(name = "drink_typename")
    private String drinkTypeName;

    @Column(name = "drink_name")
    private String drinkName;

    @Column(name = "drink_alcohol")
    private Float drinkAlcohol;

    @Column(name = "drink_picture")
    private String drinkPicture;
}
