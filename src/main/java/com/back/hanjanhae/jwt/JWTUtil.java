package com.back.hanjanhae.jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JWTUtil {
    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret){
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public Long getUsersId(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("usersId", Long.class);
    }

    public String getUsersSocialId(String token) { //유저 네임 검증
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("usersSocialId",String.class);


    }
    public String getUsersEmail(String token) {//롤 값을 뽑아냄

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("usersEmail", String.class);
    }
    public Boolean isExpired(String token) {//토큰 만료 검증

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public String createJwt(Long usersId, String usersSocialId, String usersEmail, Long expireMs ){ //토큰 생성 메소드

        System.out.println("JWTUtil createJwt작동");
        return Jwts.builder()//토큰생성?
                .claim("usersId", usersId)//키(페이로드부분)에 데이터 넣기
                .claim("usersSocialId", usersSocialId)
                .claim("usersEmail", usersEmail)
                .issuedAt(new Date(System.currentTimeMillis()))//토큰이 언제 발행되었늦지
                .expiration(new Date(System.currentTimeMillis()+expireMs))//언제 소멸될 것인지 설정
                .signWith(secretKey)//secretKey를 통해 암호화
                .compact();//문자열 형태로 반환

    }

    public SecretKey getSecretKey() {
        return this.secretKey;
    }

}