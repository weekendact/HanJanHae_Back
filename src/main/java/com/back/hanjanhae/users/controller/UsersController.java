package com.back.hanjanhae.users.controller;

import com.back.hanjanhae.users.service.UsersRegisterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UsersController {

    private final UsersRegisterService usersRegisterService;

    public UsersController(UsersRegisterService usersRegisterService) {
        this.usersRegisterService = usersRegisterService;
    }
}
