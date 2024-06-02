package com.back.hanjanhae.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersResultLoginDTO{

    private String usersSocialId;
    private String usersSocialEmail;
    private final String url = "/login";
}
