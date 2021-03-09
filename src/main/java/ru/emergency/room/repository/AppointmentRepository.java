package ru.emergency.room.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.emergency.room.entity.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

    Appointment findById(int id);
    
    @Override
    List<Appointment> findAll();
}
