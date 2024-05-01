package com.back.hanjanhae.users.model.repository;

import com.back.hanjanhae.users.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Long, Users> {
}
