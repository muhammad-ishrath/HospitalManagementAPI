
package com.example.resource;

import com.example.daoImpl.PrescriptionDAOImpl;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Prescription;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/prescriptions")
public class PrescriptionResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionResource.class);
    private PrescriptionDAOImpl prescriptionDAO = new PrescriptionDAOImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getAllPrescriptions() {
        LOGGER.info("Request received: GET /prescriptionId");
        return prescriptionDAO.getAllPrescriptions();
    }

    @GET
    @Path("/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prescription getPrescriptionById(@PathParam("prescriptionId") int prescriptionId) {
        LOGGER.info("Request received: GET /prescriptionId/{}", prescriptionId);
        Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
        if (prescription != null) {
            return prescription;
        } else {
            throw new ResourceNotFoundException("Prescription with ID " + prescriptionId + " not found.");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        LOGGER.info("Request received: POST /prescriptionId - payload: {}", prescription);
        prescriptionDAO.addPrescription(prescription);
        return Response.status(Response.Status.CREATED).entity(prescription + " Successfully Added").build();
    }

    @PUT
    @Path("/{prescriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("prescriptionId") int prescriptionId, Prescription updatedPrescription) {
         LOGGER.info("Request received: PUT /prescriptionId/{} - payload: {}", prescriptionId, updatedPrescription);
        Prescription existingBiling = prescriptionDAO.getPrescriptionById(prescriptionId);

        if (existingBiling != null) {
            updatedPrescription.setId(prescriptionId);
            prescriptionDAO.updatePrescription(updatedPrescription);
            return Response.status(Response.Status.OK).entity(prescriptionId + " Successfully Updated").build();
        } else {
            throw new ResourceNotFoundException("Prescription not found.");
        }
    }

    @DELETE
    @Path("/{prescriptionId}")
    public Response deletePrescription(@PathParam("prescriptionId") int prescriptionId) {
        LOGGER.info("Request received: DELETE /prescriptionId/{}", prescriptionId);
        Prescription existingBiling = prescriptionDAO.getPrescriptionById(prescriptionId);
        if (existingBiling != null) {
            prescriptionDAO.deletePrescription(prescriptionId);
            return  Response.status(Response.Status.OK).entity(prescriptionId + " Successfully deleted").build();
        } else {
            throw new ResourceNotFoundException("Prescription with ID " + prescriptionId + " not found.");
        }
    }
}
