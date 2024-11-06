
package com.example.dao;


import com.example.model.Doctor;
import java.util.List;

public interface DoctorDAO {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(int id);
    void addDoctor(Doctor doctor);
    void updateDoctor(Doctor updatedDoctor);
    void deleteDoctor(int id);
    int getNextDoctorId();
}
