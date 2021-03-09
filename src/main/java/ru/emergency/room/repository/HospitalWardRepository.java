package ru.emergency.room.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.emergency.room.entity.HospitalWard;

public interface HospitalWardRepository extends JpaRepository<HospitalWard, Integer> {

    HospitalWard findById(int id);

    @Override
    List<HospitalWard> findAll();
}
