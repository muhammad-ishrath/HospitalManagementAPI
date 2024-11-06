
package com.example.dao;


import com.example.model.Patient;
import java.util.List;

public interface PatientDAO {
    List<Patient> getAllPatients();
    Patient getPatientById(int id);
    void addPatient(Patient patient);
    void updatePatient(Patient updatedPatient);
    void deletePatient(int id);
    int getNextPatientId();
}
