package com.back.hanjanhae.jwt;

import com.back.hanjanhae.users.dto.CustomUserDetails;
import com.back.hanjanhae.users.dto.UsersSocialSaveDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //프론트에서 보내주는 값의 Content-Type
        String contentType = request.getContentType();

        // userId와 userEmail의 값을 담을 String
        String reqUserId ="";
        String reqUserEmail="";

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

            // usersSocialId와 usersEmail 값을 추출
            reqUserId = rootNode.get("usersSocialId").asText();
            reqUserEmail = rootNode.get("usersEmail").asText();

        }


            String authorization = request.getHeader("Authorization"); //requset에서 Authorization로 된 헤더를 찾음

            //Authorization 헤더 검증
            if (authorization == null || !authorization.startsWith("Bearer ")) {


                String token =jwtUtil.createJwt(reqUserId,reqUserEmail,60*60*60*10L); //토큰 생성
                System.out.println("JWTFilter에서 만든 JWT: "+token);

                response.addHeader("Authorization","Bearer "+token); //헤더 이름 , 인증방식 + 토큰

                request.setAttribute("usersSocialId", reqUserId);
                request.setAttribute("usersEmail", reqUserEmail);
                filterChain.doFilter(request, response);
                return;
            }

            System.out.println("authorization now");
            String token = authorization.split(" ")[1];//공백 부분 제거 후 뒷부분 token으로 받음

            if (jwtUtil.isExpired(token)) {

                System.out.println("token expired");
                filterChain.doFilter(request, response);

                //조건이 해당되면 메소드 종료 (필수)
                return;
            }

            //JwtUtil로 JWT의 담겨있는 id와 email 추출
            String usersSocialId = jwtUtil.getUserId(token);
            String usersEmail = jwtUtil.getUserEmail(token);

            System.out.println("userId: " + usersSocialId + " userEmail: " + usersEmail);

            //userDTO에 방금 전에 생성한 username과 role값 넣기
            UsersSocialSaveDTO userDTO = new UsersSocialSaveDTO();
            userDTO.setUsersSocialId(usersSocialId);
            userDTO.setUsersEmail(usersEmail);

            //1. CustomUserDetails에 userEntity 담기
            CustomUserDetails customUserDetails = new CustomUserDetails(userDTO);

            //2. 1번에서 생성한 CustomUserDetails을 넣어 스프링 시큐리티 인증 토큰 생성
            Authentication authToken =
                    new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());


            //3. 2번에서 생성한 토큰으로 세션에 사용자 등록
            SecurityContextHolder.getContext().setAuthentication(authToken);

//            System.out.println("authToken :"+authToken);

            request.setAttribute("usersSocialId", usersSocialId);
            request.setAttribute("usersEmail", usersEmail);


            filterChain.doFilter(request, response);

    }
}

