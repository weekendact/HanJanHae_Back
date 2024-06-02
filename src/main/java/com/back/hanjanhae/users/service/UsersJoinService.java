package com.back.hanjanhae.users.service;

import com.back.hanjanhae.users.dto.UsersJoinDTO;
import com.back.hanjanhae.users.model.entity.UsersEntity;
import com.back.hanjanhae.users.model.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersJoinService {

    private final UsersRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersJoinService(UsersRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(UsersJoinDTO joinDTO) {

        String username = joinDTO.getUsersSocialId();
        String password = joinDTO.getUsersSocialEmail();

        Boolean isExist = userRepository.existsByUsersSocialId(username);

        if (isExist) {

            return;
        }

        UsersEntity data = new UsersEntity();

        System.out.println(username);
        System.out.println(password);

        data.setUsersSocialId(username);
        data.setUsersSocialEmail(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
