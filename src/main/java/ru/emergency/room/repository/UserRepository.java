package ru.emergency.room.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.emergency.room.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Override
    List<User> findAll();
}
