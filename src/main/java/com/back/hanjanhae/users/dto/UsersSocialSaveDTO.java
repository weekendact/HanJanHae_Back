package com.back.hanjanhae.users.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersSocialSaveDTO {

    private Long id;

    private String usersSocialId;

    private String usersEmail;
}
