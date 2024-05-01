package users.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.model.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Long, Users> {
}
