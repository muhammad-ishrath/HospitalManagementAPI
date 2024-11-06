
package com.example.daoImpl;

import com.example.dao.AppointmentDAO;
import com.example.model.Appointment;
import com.example.model.Doctor;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppointmentDAOImpl implements AppointmentDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentDAOImpl.class);
    private static List<Appointment> appointments = new ArrayList<>();
    
    static {
        Patient patient2 = new Patient(123,"ay", "07198re8wrew", "Colombo", "Medical History", "Healthstatus");
        Doctor doctor1 = new Doctor(1242,"Skin Specialist", "Ahmed", "02222", "Dehiwala");
        appointments.add(new Appointment(11777,"March-13", "11:15am", patient2, doctor1));
        
        Patient patient4 = new Patient(12322,"ay", "07198re8wrew", "Colombo", "Medical History", "Healthstatus");
        Doctor doctor3 = new Doctor(124222,"Skin Specialist", "Ahmed", "02222", "Dehiwala");
        appointments.add(new Appointment(11778,"April-03", "09:05am", patient4, doctor3));
    }
    
    @Override
    public List<Appointment> getAllAppointments(){
        LOGGER.info("Getting all appointments");
        return appointments;
    }
    
    @Override
    public Appointment getAppointmentById(int id) {
        LOGGER.info("Getting appointment with ID: {}", id);
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id) {
                return appointment;
            }
        }
        return null;
    }

    @Override
    public void addAppointment(Appointment appointment) {
        LOGGER.info("Adding appointment: {}", appointment);
        int newAppointmentId = getNextAppointmentId();
        appointment.setId(newAppointmentId);
        appointments.add(appointment);
    }

    @Override
    public void updateAppointment(Appointment updatedAppointment) {
        LOGGER.info("Updating appointment: {}", updatedAppointment);
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getId() == updatedAppointment.getId()) {
                appointments.set(i, updatedAppointment);
                System.out.println("Appointment updated: " + updatedAppointment);
                return;
            }
        }
    }

    @Override
    public void deleteAppointment(int id) {
        LOGGER.info("Deleting appointment with ID: {}", id);
        appointments.removeIf(appointment -> appointment.getId() == id);
    }
    
    @Override
    public int getNextAppointmentId() {
        LOGGER.info("Getting next appointment ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxAppointmentId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Appointment appointment : appointments) {
            int appointmentId = appointment.getId();
            if (appointmentId > maxAppointmentId) {
                maxAppointmentId = appointmentId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxAppointmentId + 1;
    }
}
