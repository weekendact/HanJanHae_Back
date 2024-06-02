package com.back.hanjanhae.users.model.repository;

import com.back.hanjanhae.users.model.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Long> {
    Boolean existsByUsersSocialId(String username);

    UsersEntity findByUsersSocialId(String username);
    UsersEntity findByUsersId(Long username);
}
