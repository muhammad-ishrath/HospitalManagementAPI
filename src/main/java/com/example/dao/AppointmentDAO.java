
package com.example.dao;

import com.example.model.Appointment;
import java.util.List;


public interface AppointmentDAO {
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(int id);
    void addAppointment(Appointment appointment);
    void updateAppointment(Appointment updatedAppointment);
    void deleteAppointment(int id);
    int getNextAppointmentId(); 
}
