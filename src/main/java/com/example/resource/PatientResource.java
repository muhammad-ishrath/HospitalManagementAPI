
package com.example.resource;

import com.example.daoImpl.PatientDAOImpl;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Patient;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/patients")
public class PatientResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientResource.class);
    private PatientDAOImpl patientDAO = new PatientDAOImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getAllPatients() {
        LOGGER.info("Request received: GET /patients");
        return patientDAO.getAllPatients();
    }

    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("patientId") int patientId) {
        LOGGER.info("Request received: GET /patients/{}", patientId);
        Patient patient = patientDAO.getPatientById(patientId);
        if (patient != null) {
            return patient;
        } else {
            throw new ResourceNotFoundException("Patient with ID " + patientId + " not found.");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        LOGGER.info("Request received: POST /patients - payload: {}", patient);
        patientDAO.addPatient(patient);
        return Response.status(Response.Status.CREATED).entity(patient + " Successfully Added").build();
    }

    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("patientId") int patientId, Patient updatedPatient) {
        LOGGER.info("Request received: PUT /patients/{} - payload: {}", patientId, updatedPatient);
        Patient existingPatient = patientDAO.getPatientById(patientId);

        if (existingPatient != null) {
            updatedPatient.setId(patientId);
            patientDAO.updatePatient(updatedPatient);
            return Response.status(Response.Status.OK).entity(patientId + " Successfully Updated").build();
        } else {
            throw new ResourceNotFoundException("Patient not found.");
        }
    }

    @DELETE
    @Path("/{patientId}")
    public Response deletePatient(@PathParam("patientId") int patientId) {
        LOGGER.info("Request received: DELETE /patients/{}", patientId);
        Patient existingPatient = patientDAO.getPatientById(patientId);
        if(existingPatient != null) {
            patientDAO.deletePatient(patientId);
            return Response.status(Response.Status.OK).entity(existingPatient + " Successfully Deleted").build();
        } else {
            throw new ResourceNotFoundException("Patient with ID " + patientId + " not found.");
        }
    }
}
