package ru.emergency.room.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.emergency.room.entity.HospitalWard;
import ru.emergency.room.repository.HospitalWardRepository;

@Service
public class HospitalWardService {

    @Autowired
    private HospitalWardRepository hospitalWardRepository;

    public HospitalWard findById(int id) {
        return hospitalWardRepository.findById(id);
    }

    public List<HospitalWard> findAll() {
        return hospitalWardRepository.findAll();
    }

    public HospitalWard save(HospitalWard hospitalWard) {
        return hospitalWardRepository.save(hospitalWard);
    }

    public void delete(HospitalWard hospitalWard) {
        hospitalWardRepository.delete(hospitalWard);
    }
}
