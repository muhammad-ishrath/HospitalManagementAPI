
package com.example.resource;

import com.example.daoImpl.DoctorDAOImpl;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Doctor;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Path("/doctors")
public class DoctorResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorResource.class);
    private DoctorDAOImpl doctorDAO = new DoctorDAOImpl();
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> getAllDoctors() {
        LOGGER.info("Request received: GET /doctors");
        return doctorDAO.getAllDoctors();
    }

    @GET
    @Path("/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doctor getDoctorById(@PathParam("doctorId") int doctorId) {
        LOGGER.info("Request received: GET /doctors/{}", doctorId);
        Doctor doctor = doctorDAO.getDoctorById(doctorId);
        if (doctor != null) {
            return doctor;
        } else {
            throw new ResourceNotFoundException("Doctor with ID " + doctorId + " not found.");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        LOGGER.info("Request received: POST /doctors - payload: {}", doctor);
        doctorDAO.addDoctor(doctor);
        return Response.status(Response.Status.CREATED).entity(doctor + " Successfully Added").build();
    }

    @PUT
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("doctorId") int doctorId, Doctor updatedDoctor) {
        LOGGER.info("Request received: PUT /doctors/{} - payload: {}", doctorId, updatedDoctor);
        Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);

        if (existingDoctor != null) {
            updatedDoctor.setId(doctorId);
            doctorDAO.updateDoctor(updatedDoctor);
            return Response.status(Response.Status.OK).entity(doctorId + " Successfully Updated").build();
        } else {
            throw new ResourceNotFoundException("Doctor not found.");
        }
        
    }

    @DELETE
    @Path("/{doctorId}")
    public Response deleteDoctor(@PathParam("doctorId") int doctorId) {
        LOGGER.info("Request received: DELETE /doctors/{}", doctorId);
        Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);
        if(existingDoctor != null){
            doctorDAO.deleteDoctor(doctorId);
            return  Response.status(Response.Status.OK).entity(existingDoctor + " Successfully deleted").build();
        } else {
            throw new ResourceNotFoundException("Doctor with ID " + doctorId + " not found.");
        }
    }
}
