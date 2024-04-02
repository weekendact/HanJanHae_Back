package com.back.hanjanhae.users.service;

import com.back.hanjanhae.jwt.JWTUtil;
import com.back.hanjanhae.users.dto.UsersSocialSaveDTO;
import com.back.hanjanhae.users.model.entity.Users;
import com.back.hanjanhae.users.model.repository.UsersRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;


    public UsersService( UsersRepository usersRepository) {

        this.usersRepository = usersRepository;
    }



    @Transactional
    public int socialSignUp(UsersSocialSaveDTO usersSocialSaveDTO) {

        if (usersRepository.findByUsersSocialIdAndUsersEmail(usersSocialSaveDTO.getUsersSocialId(), usersSocialSaveDTO.getUsersEmail()) != null) {
            // 만약 소셜 아이디, 이메일로 찾은 유저가 있다면 return 202
            return 202;
        } else {
            System.out.println("socialSignUp가동중");
            Users users = new Users();
            String id =usersSocialSaveDTO.getUsersSocialId();
            String email = usersSocialSaveDTO.getUsersEmail();


            users.setUsersSocialId(id);
            users.setUsersEmail(email);


            usersRepository.save(users);

            return 201;
        }
    }




    public int deleteUsers(Long usersId) {
        if (usersRepository.findByUsersId(usersId) != null) {
            usersRepository.deleteById(usersId);

            return 204;
        }
        else
            return 404;
    }

}
