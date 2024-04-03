package com.back.hanjanhae.users.service;

import com.back.hanjanhae.jwt.JWTFilter;
import com.back.hanjanhae.jwt.JWTUtil;
import com.back.hanjanhae.users.dto.UsersSaveDetailDTO;
import com.back.hanjanhae.users.dto.UsersSocialSaveDTO;
import com.back.hanjanhae.users.model.entity.Users;
import com.back.hanjanhae.users.model.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;


    public UsersService(UsersRepository usersRepository) {

        this.usersRepository = usersRepository;
    }



    @Transactional
    public boolean socialSignUp(UsersSocialSaveDTO usersSocialSaveDTO) {

        if (usersRepository.findByUsersSocialIdAndUsersEmail(usersSocialSaveDTO.getUsersSocialId(), usersSocialSaveDTO.getUsersEmail()) != null) {
            // 만약 소셜 아이디, 이메일로 찾은 유저가 있다면 return 202
            return false;
        } else {
            System.out.println("socialSignUp가동중");

            Users users = new Users();
            String id =usersSocialSaveDTO.getUsersSocialId();
            String email = usersSocialSaveDTO.getUsersEmail();

            users.setUsersSocialId(id);
            users.setUsersEmail(email);

            usersRepository.save(users);

            return true;
        }
    }



    public boolean nicknameDuplicate(String usersNickname) {
        return usersRepository.existsByUsersNickname(usersNickname); // 닉네임이 있니 없니 boolean
    }

    public boolean usersSaveDetail(UsersSaveDetailDTO usersSaveDetailDTO, String usersEmail) {
        Users users = usersRepository.findByUsersEmail(usersEmail);

        if (users != null) {
            users.setUsersNickname(usersSaveDetailDTO.getUsersNickname());
            users.setUsersAge(usersSaveDetailDTO.getUsersAge());
            users.setUsersGender(usersSaveDetailDTO.getUsersGender());

            usersRepository.save(users);

            return true;
        }
        else
            return false;
    }


    public boolean deleteUsers(String usersEmail) {

        if (usersRepository.existsByUsersEmail(usersEmail)) {
            usersRepository.deleteByUsersEmail(usersEmail);
            return true;
        }
        else
            return false;
    }

}
