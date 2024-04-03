package com.back.hanjanhae.config;

import com.back.hanjanhae.jwt.JWTFilter;
import com.back.hanjanhae.jwt.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    final private JWTUtil jwtUtil;

    public SecurityConfig(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .cors((cors)->cors
                        .configurationSource(new CorsConfigurationSource() {

                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                CorsConfiguration configuration = new CorsConfiguration();
                                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5000"));
                                configuration.setAllowedMethods(Collections.singletonList(""));
                                configuration.setAllowCredentials(true);
                                configuration.setAllowedHeaders(Collections.singletonList(""));
                                configuration.setMaxAge(3600L);

                                configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                                return configuration;
                            }
                        }));

        System.out.println("SecurityFilterChain 정상작동");

        //csrf disable
        http.csrf((auth) -> auth.disable());
        //Form 로그인 방식 disable
        http.formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/socialSignUp").permitAll()
                .anyRequest().authenticated());

        //세션 설정
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        http
                .addFilterAt(new JWTFilter(jwtUtil),  UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}