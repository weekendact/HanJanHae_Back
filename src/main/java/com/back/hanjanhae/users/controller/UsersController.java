package com.back.hanjanhae.users.controller;

import com.back.hanjanhae.users.dto.UsersSaveDetailDTO;
import com.back.hanjanhae.users.dto.UsersSocialSaveDTO;
import com.back.hanjanhae.users.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    private Long issueUserIdFromToken(HttpServletRequest request) {
        return (Long) request.getAttribute("usersId");
    }

    @PostMapping("/socialLogIn")
    public ResponseEntity<Void> socialLogin(HttpServletRequest request) {
        return usersService.socialLogin(issueUserIdFromToken(request)) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/socialSignUp")
    public ResponseEntity<Void> socialSignUp(@RequestBody UsersSocialSaveDTO usersSocialSaveDTO, HttpServletRequest request) {
        usersSocialSaveDTO.setUsersSocialId((String) request.getAttribute("usersSocialId"));
        usersSocialSaveDTO.setUsersEmail((String) request.getAttribute("usersEmail"));

        return usersService.socialSignUp(usersSocialSaveDTO) ?
                ResponseEntity.status(HttpStatus.CREATED).build() :
                ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/nicknameDuplicate")
    public ResponseEntity<Void> nicknameDuplicate(@RequestParam String usersNickname) {
        return usersService.nicknameDuplicate(usersNickname) ?
                ResponseEntity.status(HttpStatus.CONFLICT).build() : // 닉네임이 있음
                ResponseEntity.status(HttpStatus.OK).build(); // 닉네임이 없음
    }

    @PostMapping("/usersSaveDetail")
    public ResponseEntity<Void> usersSaveDetail(@RequestBody UsersSaveDetailDTO usersSaveDetailDTO, HttpServletRequest request) {
        return usersService.saveUsersDetails(usersSaveDetailDTO,issueUserIdFromToken(request)) ?
                ResponseEntity.status(HttpStatus.CREATED).build() : // nickname, age, gender 저장 완료
                ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 뭔가 잘못됨
    }

    @DeleteMapping("/deleteUsers")
    public ResponseEntity<Void> deleteUsers(HttpServletRequest request) {
       return  usersService.deleteUsers(issueUserIdFromToken(request)) ?
             ResponseEntity.status(HttpStatus.NO_CONTENT).build() : // 삭제 완료
             ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 객체가 없음
       }
}

