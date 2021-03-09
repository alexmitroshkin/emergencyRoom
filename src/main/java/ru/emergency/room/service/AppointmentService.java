package ru.emergency.room.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.emergency.room.entity.Appointment;
import ru.emergency.room.entity.Search;
import ru.emergency.room.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private EntityManager em;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment findById(int id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> findAll(Sort sort) {
        return appointmentRepository.findAll(sort);
    }

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void delete(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

    public List<Appointment> search(Search search) {
        String sql = "SELECT a FROM Appointment a WHERE 1 = 1";
        if (!search.getFamily().equals("")){
            sql += " and a.lastName like :fam";
        }
        if (!search.getDiagnos().equals("")){
            sql += " and a.diagnosis like :diagnos";
        }
        TypedQuery<Appointment> query = em.createQuery(sql, Appointment.class);
        if (!search.getFamily().equals("")){
            query.setParameter("fam", search.getFamily().replace("*", "%"));
        }
        if (!search.getDiagnos().equals("")){
            query.setParameter("diagnos", search.getDiagnos().replace("*", "%"));
        }
        return query.getResultList();
    }

}
