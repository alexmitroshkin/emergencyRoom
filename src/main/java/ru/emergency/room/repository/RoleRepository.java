package ru.emergency.room.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.emergency.room.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Role findByName(String name);

    @Override
    List<Role> findAll();
}
