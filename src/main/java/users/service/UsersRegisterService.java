package users.service;

import org.springframework.stereotype.Service;
import users.model.repository.UsersRepository;

@Service
public class UsersRegisterService {

    private final UsersRepository usersRepository;

    public UsersRegisterService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
