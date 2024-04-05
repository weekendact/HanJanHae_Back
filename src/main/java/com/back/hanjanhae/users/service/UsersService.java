package com.back.hanjanhae.users.service;

import com.back.hanjanhae.users.dto.UsersSaveDetailDTO;
import com.back.hanjanhae.users.dto.UsersSocialSaveDTO;
import com.back.hanjanhae.users.model.entity.Users;
import com.back.hanjanhae.users.model.repository.UsersRepository;

import jakarta.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users handleSocialSignUp(String usersSocialId, String usersEmail) {
        return usersRepository.findByUsersSocialIdAndUsersEmail(usersSocialId, usersEmail)
                .orElseGet(() -> {
                    Users users = new Users();
                    users.setUsersSocialId(usersSocialId);
                    users.setUsersEmail(usersEmail);
                    usersRepository.save(users);
                    return users;
                });
    }

    public boolean socialLogin(Long usersId) {
        return usersRepository.findByUsersId(usersId).isPresent();
    }

    @Transactional
    public boolean socialSignUp(UsersSocialSaveDTO usersSocialSaveDTO) {
        if (usersRepository.findByUsersSocialIdAndUsersEmail(usersSocialSaveDTO.getUsersSocialId(), usersSocialSaveDTO.getUsersEmail()).isPresent()) {
            return false; // 찾은 socialId, Email이 있다면
        } else {
            handleSocialSignUp(usersSocialSaveDTO.getUsersSocialId(), usersSocialSaveDTO.getUsersEmail());
            return true;
        }
    }

    public boolean nicknameDuplicate(String usersNickname) {
        return usersRepository.existsByUsersNickname(usersNickname); // 닉네임이 있니 없니 boolean
    }

    public boolean saveUsersDetails(UsersSaveDetailDTO usersSaveDetailDTO, Long usersId) {
        return usersRepository.findByUsersId(usersId).map(users -> {
            users.setUsersNickname(usersSaveDetailDTO.getUsersNickname());
            users.setUsersAge(usersSaveDetailDTO.getUsersAge());
            users.setUsersGender(usersSaveDetailDTO.getUsersGender());
            usersRepository.save(users);
            return true;
        }).orElse(false);
        }

    @Transactional
    public boolean deleteUsers(Long usersId) {
        try {
            usersRepository.deleteById(usersId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}

