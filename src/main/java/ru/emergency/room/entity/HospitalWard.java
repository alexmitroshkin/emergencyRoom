package ru.emergency.room.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * больничная палата
 */
@Entity
@Table(name = "hospital_ward")
public class HospitalWard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospitalWard_id")
    private int id;
    private int floor;
    private int number;
    @OneToMany(mappedBy = "hospitalWard", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public HospitalWard() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        String numberHospital = "" + number;
        while (numberHospital.length() < 2) {
            numberHospital = "0" + numberHospital;
        }
        return floor + numberHospital;
    }

}
