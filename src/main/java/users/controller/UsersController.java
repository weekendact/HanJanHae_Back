package users.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import users.service.UsersRegisterService;

@RestController
@RequestMapping("users")
public class UsersController {

    private final UsersRegisterService usersRegisterService;

    public UsersController(UsersRegisterService usersRegisterService) {
        this.usersRegisterService = usersRegisterService;
    }
    
}
