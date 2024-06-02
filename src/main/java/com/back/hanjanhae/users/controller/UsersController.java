package com.back.hanjanhae.users.controller;

import com.back.hanjanhae.users.dto.UsersJoinDTO;
import com.back.hanjanhae.users.service.UsersJoinService;
import com.back.hanjanhae.users.service.UsersRegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("users")
public class UsersController {

    private final UsersRegisterService usersRegisterService;
    private final UsersJoinService usersJoinService;


    public UsersController(UsersRegisterService usersRegisterService, UsersJoinService usersJoinService) {
        this.usersRegisterService = usersRegisterService;
        this.usersJoinService = usersJoinService;

    }

    @PostMapping("/join")
    public String joinProcess(@RequestBody UsersJoinDTO usersJoinDTO) {

        System.out.println(usersJoinDTO.getUsersSocialId());
        usersJoinService.joinProcess(usersJoinDTO);

        return "ok";
    }
}
