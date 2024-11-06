
package com.example.daoImpl;

import com.example.dao.PatientDAO;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PatientDAOImpl extends PersonDAOImpl implements PatientDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientDAOImpl.class);
    private static List<Patient> patients = new ArrayList<>();
    
    static {
        patients.add(new Patient(1000,"Jane Doe", "jane@example.com", "Colombo", "Medical history", "Health status"));
    }
    
    @Override
    public List<Patient> getAllPatients(){
        LOGGER.info("Getting all patients");
        return patients;
    }
    
    @Override
    public Patient getPatientById(int id) {
        LOGGER.info("Getting patient with ID: {}", id);
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public void addPatient(Patient patient) {
        LOGGER.info("Adding patient: {}", patient);
        int newPatientId = getNextPatientId();
        patient.setId(newPatientId);
        patients.add(patient);
    }

    @Override
    public void updatePatient(Patient updatedPatient) {
        LOGGER.info("Updating patient: {}", updatedPatient);
        for (int i = 0; i < patients.size(); i++) {
            Patient person = patients.get(i);
            if (person.getId()== updatedPatient.getId()) {
                patients.set(i, updatedPatient);
                System.out.println("Patient updated: " + updatedPatient);
                return;
            }
        }
    }

    @Override
    public void deletePatient(int id) {
        LOGGER.info("Deleting patient with ID: {}", id);
        patients.removeIf(person -> person.getId() == id);
    }
    
    @Override
    public int getNextPatientId() {
        LOGGER.info("Getting next patient ID");
        // Initialize maxPatientId with a value lower than any possible ID
        int maxPatientId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum ID
        for (Patient patient : patients) {
            int patientId = patient.getId();
            if (patientId > maxPatientId) {
                maxPatientId = patientId;
            }
        }

        // Increment the maximum ID to get the next available ID
        return maxPatientId + 1;
    }
}
