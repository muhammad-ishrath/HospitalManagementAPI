
package com.example.daoImpl;
//package com.example.model;

import com.example.dao.DoctorDAO;
import com.example.model.Doctor;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DoctorDAOImpl extends PersonDAOImpl implements DoctorDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDAOImpl.class);
    private static List<Doctor> doctors = new ArrayList<>();
        
    static {
        doctors.add(new Doctor(1, "Dermatology", "Dr. Jane Smith", "555-1234", "Hospital A"));
        doctors.add(new Doctor(2, "Cardiology", "Dr. Michael Brown", "555-5678", "Hospital B"));
    }
        
    @Override
    public List<Doctor> getAllDoctors(){
        LOGGER.info("Getting all doctors");
        return doctors;
    }
    
    @Override
    public Doctor getDoctorById(int id) {
        LOGGER.info("Getting doctor with ID: {}", id);
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public void addDoctor(Doctor doctor) {
        LOGGER.info("Adding doctor: {}", doctor);
        int newDoctorId = getNextDoctorId();
        doctor.setId(newDoctorId);
        doctors.add(doctor);
    }

    @Override
    public void updateDoctor(Doctor updatedDoctor) {
        LOGGER.info("Updating doctor: {}", updatedDoctor);
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            if (doctor.getId() == updatedDoctor.getId()) {
                doctors.set(i, updatedDoctor);
                System.out.println("Doctor updated: " + updatedDoctor);
                return;
            }
        }
    }

    @Override
    public void deleteDoctor(int id) {
        LOGGER.info("Deleting doctor with ID: {}", id);
        doctors.removeIf(doctor -> doctor.getId() == id);
    }
    
    @Override
    public int getNextDoctorId() {
        LOGGER.info("Getting next doctor ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxDoctorId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Doctor doctor : doctors) {
            int appointmentId = doctor.getId();
            if (appointmentId > maxDoctorId) {
                maxDoctorId = appointmentId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxDoctorId + 1;
    }
}
