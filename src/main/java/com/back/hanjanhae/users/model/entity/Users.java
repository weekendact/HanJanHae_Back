package com.back.hanjanhae.users.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long usersId;

    @Column(name = "users_social_id")
    private String usersSocialId;

    @Column(name = "users_email")
    private String usersEmail;

    @Column(name = "users_nickname")
    private String usersNickname;

    @Column(name = "users_age")
    private Integer usersAge;

    @Column(name = "users_gender")
    private Character usersGender;
}
