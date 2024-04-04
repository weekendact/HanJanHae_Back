package com.back.hanjanhae.users.service;

import com.back.hanjanhae.users.dto.UsersSaveDetailDTO;
import com.back.hanjanhae.users.dto.UsersSocialSaveDTO;
import com.back.hanjanhae.users.model.entity.Users;
import com.back.hanjanhae.users.model.repository.UsersRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // UsersService.java

    public Users handleSocialLogin(String usersSocialId, String usersEmail) {
        // 사용자의 소셜 ID와 이메일을 기반으로 데이터베이스에서 사용자를 찾습니다.
        Optional<Users> existingUser = usersRepository.findByUsersSocialIdAndUsersEmail(usersSocialId, usersEmail);

        Users users;
        if (existingUser.isPresent()) {
            users = existingUser.get();
        } else {
            users = new Users();
            users.setUsersSocialId(usersSocialId);
            users.setUsersEmail(usersEmail);

            usersRepository.save(users);
        }
        return users;
    }


    @Transactional
    public boolean socialLogin(UsersSocialSaveDTO usersSocialSaveDTO) {
        if (usersRepository.findByUsersSocialIdAndUsersEmail(usersSocialSaveDTO.getUsersSocialId(), usersSocialSaveDTO.getUsersEmail()) != null) {
            // 만약 소셜 아이디, 이메일로 찾은 유저가 있다면 return 202
            return false;
        } else {
            Users users = new Users();
            users.setUsersSocialId(usersSocialSaveDTO.getUsersSocialId());
            users.setUsersEmail(usersSocialSaveDTO.getUsersEmail());

            usersRepository.save(users);

            return true;
        }
    }

    public boolean nicknameDuplicate(String usersNickname) {
        return usersRepository.existsByUsersNickname(usersNickname); // 닉네임이 있니 없니 boolean
    }

    public boolean saveUsersDetails(UsersSaveDetailDTO usersSaveDetailDTO, Long usersId) {
        Optional<Users> optionalUser = usersRepository.findByUsersId(usersId);

        if(optionalUser.isPresent()) {
            Users users = optionalUser.get();
            users.setUsersNickname(usersSaveDetailDTO.getUsersNickname());
            users.setUsersAge(usersSaveDetailDTO.getUsersAge());
            users.setUsersGender(usersSaveDetailDTO.getUsersGender());

            usersRepository.save(users);
            return true; // 사용자 정보를 성공적으로 업데이트한 경우 true 반환
        } else {
            return false; // 사용자를 찾지 못한 경우 false 반환
        }
    }

    @Transactional
    public boolean deleteUsers(Long usersId) {
        if (usersRepository.existsByUsersId(usersId)) {
            usersRepository.deleteByUsersId(usersId);
            return true;
        }
        else
            return false;
    }

}
