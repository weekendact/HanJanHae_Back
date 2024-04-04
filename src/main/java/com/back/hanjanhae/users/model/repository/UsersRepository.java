package com.back.hanjanhae.users.model.repository;

import com.back.hanjanhae.users.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    // usersSocialId, usersEmail로 유저를 찾아서 Optional 로 반환
    Optional<Users> findByUsersSocialIdAndUsersEmail(String usersSocialId, String usersEmail);

    // usersId로 유저 찾아서 Optinal 로 반환
    Optional<Users> findByUsersId(Long usersEmail);

    // usersNickname이 있는지 확인 후 null 이면 false 있으면 true
    boolean existsByUsersNickname(String usersNickname);

    // usersEmail이 있는지 확인 후 null 이면 false 있으면 true
    boolean existsByUsersId(Long usersId);

    // usersEmail로 객체 삭제
    void deleteByUsersId(Long usersId);
}
