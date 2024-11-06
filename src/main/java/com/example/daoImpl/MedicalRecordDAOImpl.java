
package com.example.daoImpl;

import com.example.dao.MedicalRecordDAO;
import com.example.model.MedicalRecord;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MedicalRecordDAOImpl implements MedicalRecordDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordDAO.class);
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();
    
    static {
        Patient patient1 = new Patient(111114444,"Mohomed", "98920493", "Dehiwala", "Good Medica History", "success");
        medicalRecords.add(new MedicalRecord(7878748,patient1, "Diagnoses", "Treetments"));
    }
    
    @Override
    public List<MedicalRecord> getAllMedicalRecords(){
        LOGGER.info("Getting all medical records");
        return medicalRecords;
    }
    
    @Override
    public MedicalRecord getMedicalRecordById(int id) {
        LOGGER.info("Getting medical record with ID: {}", id);
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getId() == id) {
                return medicalRecord;
            }
        }
        return null;
    }

    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        LOGGER.info("Adding medical record: {}", medicalRecord);
        int newMedicalRecordId = getNextMedicalRecordId();
        medicalRecord.setId(newMedicalRecordId);
        medicalRecords.add(medicalRecord);
    }

    @Override
    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        LOGGER.info("Updating medical record: {}", updatedMedicalRecord);
        for (int i = 0; i < medicalRecords.size(); i++) {
            MedicalRecord medicalRecord = medicalRecords.get(i);
            if (medicalRecord.getId()== updatedMedicalRecord.getId()) {
                medicalRecords.set(i, updatedMedicalRecord);
                System.out.println("MedicalRecord updated: " + updatedMedicalRecord);
                return;
            }
        }
    }

    @Override
    public void deleteMedicalRecord(int id) {
        LOGGER.info("Deleting medical record with ID: {}", id);
        medicalRecords.removeIf(medicalRecord -> medicalRecord.getId()== id);
    }
    
    @Override
    public int getNextMedicalRecordId() {
        LOGGER.info("Getting next medical record ID");
        // Initialize maxMedicalRecordId with a value lower than any possible ID
        int maxMedicalRecordId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum ID
        for (MedicalRecord medicalRecord : medicalRecords) {
            int medicalRecordId = medicalRecord.getId();
            if (medicalRecordId > maxMedicalRecordId) {
                maxMedicalRecordId = medicalRecordId;
            }
        }

        // Increment the maximum ID to get the next available ID
        return maxMedicalRecordId + 1;
    }
    
}
