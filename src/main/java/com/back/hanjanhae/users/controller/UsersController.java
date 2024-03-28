package com.back.hanjanhae.users.controller;

import com.back.hanjanhae.users.dto.UsersSocialSaveDTO;
import com.back.hanjanhae.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/socialLogin")
    public ResponseEntity<String> socialLogin(@RequestBody UsersSocialSaveDTO usersSocialSaveDTO) {
        int result = usersService.socialLogin(usersSocialSaveDTO);
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
