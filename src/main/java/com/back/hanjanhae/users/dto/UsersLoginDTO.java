package com.back.hanjanhae.users.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsersLoginDTO {
    private String usersSocialId;
    private String usersSocialEmail;
}
