package com.back.hanjanhae.users.controller;

import com.back.hanjanhae.users.dto.UsersSaveDetailDTO;
import com.back.hanjanhae.users.dto.UsersSocialSaveDTO;
import com.back.hanjanhae.users.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {

        this.usersService = usersService;
    }



    @PostMapping("/socialSignUp")
    public ResponseEntity<String> socialSignUp(UsersSocialSaveDTO usersSocialSaveDTO, HttpServletRequest request) {

        String userId = (String) request.getAttribute("usersSocialId");
        String userEmail = (String) request.getAttribute("usersEmail");

        usersSocialSaveDTO.setUsersSocialId(userId);
        usersSocialSaveDTO.setUsersEmail(userEmail);

        System.out.println("Controller작동중");

        return usersService.socialSignUp(usersSocialSaveDTO) ?
                ResponseEntity.status(HttpStatus.CREATED).body("") :
                ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/nicknameDuplicate")
    public ResponseEntity<?> nicknameDuplicate(@RequestParam String usersNickname) {
        return usersService.nicknameDuplicate(usersNickname) ?
                ResponseEntity.status(HttpStatus.CONFLICT).build(): // 닉네임이 있음
                ResponseEntity.status(HttpStatus.OK).build(); // 닉네임이 없음
    }

    @PostMapping("/usersSaveDetail")
    public ResponseEntity<?> usersSaveDetail(UsersSaveDetailDTO usersSaveDetailDTO, HttpServletRequest request) {
        String usersEmail = (String) request.getAttribute("usersEmail");
        return usersService.usersSaveDetail(usersSaveDetailDTO, usersEmail) ?
                ResponseEntity.status(HttpStatus.CREATED).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/deleteUsers")
    public ResponseEntity<String> deleteUsers(HttpServletRequest request) {
        String usersEmail = (String) request.getAttribute("uesrsEmail");
       return  usersService.deleteUsers(usersEmail) ?
             ResponseEntity.status(HttpStatus.NO_CONTENT).body(""):
             ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
       }
}

