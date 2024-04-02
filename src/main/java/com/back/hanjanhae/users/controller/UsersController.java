package com.back.hanjanhae.users.controller;

import com.back.hanjanhae.users.dto.UsersSocialSaveDTO;
import com.back.hanjanhae.users.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

        int result = usersService.socialSignUp(usersSocialSaveDTO);

        System.out.println("Controller작동중");
        if (result == 201) {
            return ResponseEntity.status(HttpStatus.CREATED).body("");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
    }

    @DeleteMapping("/deleteUsers")
    public ResponseEntity<String> deleteUsers(@RequestParam Long usersId) {
       int result = usersService.deleteUsers(usersId);
       if (result == 204) {
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
       }
    }

}
