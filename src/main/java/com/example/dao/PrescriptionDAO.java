
package com.example.dao;


import com.example.model.Prescription;
import java.util.List;

public interface PrescriptionDAO {
    List<Prescription> getAllPrescriptions();
    Prescription getPrescriptionById(int id);
    void addPrescription(Prescription prescription);
    void updatePrescription(Prescription updatedPrescription);
    void deletePrescription(int id);
    int getNextPrescriptionId();
}
