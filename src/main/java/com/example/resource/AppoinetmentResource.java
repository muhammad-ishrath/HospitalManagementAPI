
package com.example.resource;
import com.example.daoImpl.AppointmentDAOImpl;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Appointment;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Path("/appointments")
public class AppoinetmentResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppoinetmentResource.class);
    private AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAllAppointments() {
        LOGGER.info("Request received: GET /appointments");
        return appointmentDAO.getAllAppointments();
    }

    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Appointment getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        LOGGER.info("Getting appointment by ID: {}", appointmentId);
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
        if (appointment != null) {
            return appointment;
        } else {
            throw new ResourceNotFoundException("Appointment with ID " + appointmentId + " not found.");
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        LOGGER.info("Request received: POST /appointments - payload: {}", appointment);
        appointmentDAO.addAppointment(appointment);
        return Response.status(Response.Status.CREATED).entity(appointment + "Successfully Added").build();
    }

    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("appointmentId") int appointmentId, Appointment updatedAppointment) {
        LOGGER.info("Request received: PUT /appointments/{} - payload: {}", appointmentId, updatedAppointment);
        Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);

        if (existingAppointment != null) {
            updatedAppointment.setId(appointmentId);
            appointmentDAO.updateAppointment(updatedAppointment);
            return Response.status(Response.Status.OK).entity(appointmentId + " Successfully Updated").build();
        } else {
             throw new ResourceNotFoundException("Appointment not found.");
        }
        
    }

    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        LOGGER.info("Request received: DELETE /appointments/{}", appointmentId);
        Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);
        if (existingAppointment != null) {
            appointmentDAO.deleteAppointment(appointmentId);
            return Response.status(Response.Status.OK).entity(existingAppointment + " Successfully deleted").build();
        } else {
            throw new ResourceNotFoundException("Appointment with ID " + appointmentId + " not found.");
        }
    }
}
