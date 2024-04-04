package com.back.hanjanhae.jwt;

import com.back.hanjanhae.users.dto.CustomUserDetails;
import com.back.hanjanhae.users.dto.UsersSocialSaveDTO;
import com.back.hanjanhae.users.model.entity.Users;
import com.back.hanjanhae.users.service.UsersService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UsersService usersService;

    public JWTFilter(JWTUtil jwtUtil, UsersService usersService) {
        this.jwtUtil = jwtUtil;
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                              HttpServletResponse response,
                                              FilterChain filterChain) throws ServletException, IOException {
        try {

            // 프론트에서 보내주는 값의 Content-Type
            String contentType = request.getContentType();

            // requset에서 Authorization로 된 헤더를 찾음
            String authorization = request.getHeader("Authorization");

            // userId와 userEmail의 값을 담을 String
            String reqUsersSocialId = "";
            String reqUsersEmail = "";


            // Authorization 헤더 검증
            if (authorization == null || !authorization.startsWith("Bearer ")) {

                // Content-Type이 application/json 인지 확인
                if (contentType != null && contentType.startsWith("application/json")) {

                    // JSON 데이터를 읽어옴
                    StringBuilder stringBuilder = new StringBuilder();
                    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                    }

                    // 읽어온 JSON 데이터 파싱
                    String json = stringBuilder.toString();
                    JsonNode rootNode = objectMapper.readTree(json);

                    // usersSocialId와 usersEmail이 있다면
                    if (rootNode.get("usersSocialId") != null && rootNode.get("usersEmail") != null) {
                        // usersSocialId와 usersEmail 값을 추출
                        reqUsersSocialId = rootNode.get("usersSocialId").asText();
                        reqUsersEmail = rootNode.get("usersEmail").asText();

                        // 회원가입 로직
                        Users user = usersService.handleSocialLogin(reqUsersSocialId, reqUsersEmail);

                        //토큰 생성
                        String token = "Bearer " + jwtUtil.createJwt(user.getUsersId(), user.getUsersSocialId(), user.getUsersEmail(), 60 * 60 * 60 * 60 * 60 * 10L);

                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.setStatus(HttpServletResponse.SC_CREATED);

                        // 조아름 씨를 위한 토큰 확인
                        System.out.println("token : " + token);

                        // 응답 바디에 JSON 형태로 토큰 값을 포함
                        Map<String, String> tokenResponse = new HashMap<>();
                        tokenResponse.put("token", token);

                        // ObjectMapper를 사용해 Map을 JSON 문자열로 변환
                        String jsonResponse = objectMapper.writeValueAsString(tokenResponse);

                        // 응답 바디에 JSON 문자열 작성
                        response.getWriter().write(jsonResponse);

                        return;
                    }

                }
                // 만약 토큰이 있다면
            } else {
                // 로그인 시 토큰이 있으면
                String token = authorization.substring(7); // bearer 다음 부분 받음

                // 예외 처리 만약 만료된 토큰에 usersSocialId, usersEmail이 유요하다면 다시 발급
                if (jwtUtil.isExpired(token)) {
                    System.out.println("token expired");
                }

                // JwtUtil로 JWT의 담겨있는 id, socialid, email 추출
                Long usersId = jwtUtil.getUsersId(token);
                String usersSocialId = jwtUtil.getUsersSocialId(token);
                String usersEmail = jwtUtil.getUsersEmail(token);

                // id, socialid, email로 dto 만들기
                UsersSocialSaveDTO usersSocialSaveDTO = new UsersSocialSaveDTO();
                usersSocialSaveDTO.setId(usersId);
                usersSocialSaveDTO.setUsersSocialId(usersSocialId);
                usersSocialSaveDTO.setUsersEmail(usersEmail);

                // 1. CustomUserDetails에 userEntity 담기
                CustomUserDetails customUserDetails = new CustomUserDetails(usersSocialSaveDTO);

                // 2. 1번에서 생성한 CustomUserDetails을 넣어 스프링 시큐리티 인증 토큰 생성
                Authentication authToken =
                        new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

                // 3. 2번에서 생성한 토큰으로 세션에 사용자 등록
                SecurityContextHolder.getContext().setAuthentication(authToken);

                request.setAttribute("usersId", usersId);
                request.setAttribute("usersSocialId", usersSocialId);
                request.setAttribute("usersEmail", usersEmail);

                filterChain.doFilter(request, response);

                return;
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
