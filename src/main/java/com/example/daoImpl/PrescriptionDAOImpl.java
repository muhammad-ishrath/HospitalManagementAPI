
package com.example.daoImpl;

import com.example.dao.PrescriptionDAO;
import com.example.model.Patient;
import com.example.model.Prescription;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PrescriptionDAOImpl implements PrescriptionDAO{
     private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionDAOImpl.class);
    private static List<Prescription> prescriptions = new ArrayList<>();
    
    static {
        Patient patient1 = new Patient(1223213,"Kamal Hasan", "077 123 123", "Kandy","Bla Bla", "Falier");
        prescriptions.add(new Prescription(10000202,patient1, "Heart Sergery", "bla bla", "bla bla", "4 months"));
    }
    
    @Override
    public List<Prescription> getAllPrescriptions(){
        LOGGER.info("Getting all prescriptions");
        return prescriptions;
    }
    
    @Override
    public Prescription getPrescriptionById(int id) {
        LOGGER.info("Getting prescription with ID: {}", id);
        for (Prescription patient : prescriptions) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public void addPrescription(Prescription prescription) {
        LOGGER.info("Adding prescription: {}", prescription);
        int newPrescriptionId = getNextPrescriptionId();
        prescription.setId(newPrescriptionId);
        prescriptions.add(prescription);
    }

    @Override
    public void updatePrescription(Prescription updatedPrescription) {
        LOGGER.info("Updating prescription: {}", updatedPrescription);
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription prescription = prescriptions.get(i);
            if (prescription.getId() == updatedPrescription.getId()) {
                prescriptions.set(i, updatedPrescription);
                System.out.println("Prescription updated: " + updatedPrescription);
                return;
            }
        }
    }

    @Override
    public void deletePrescription(int id) {
        LOGGER.info("Deleting prescription with ID: {}", id);
        prescriptions.removeIf(prescription -> prescription.getId() == id);
    }
    
    @Override
    public int getNextPrescriptionId() {
        LOGGER.info("Getting next prescription ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxPrescriptionId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Prescription prescription : prescriptions) {
            int prescriptionId = prescription.getId();
            if (prescriptionId > maxPrescriptionId) {
                maxPrescriptionId = prescriptionId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxPrescriptionId + 1;
    }
}
