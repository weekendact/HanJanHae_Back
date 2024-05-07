package com.back.hanjanhae.jwt;
import java.util.Date;

import lombok.Data;

@Data
public class JwtTokenDto {
    private String accessToken;
    private String refreshToken;
    private User user;
    private Date expiresIn;

    public JwtTokenDto(String accessToken, String refreshToken, Date expiresIn, User user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.user = user;
    }
}
