
package com.example.resource;

import com.example.daoImpl.MedicalRecordDAOImpl;
import com.example.exception.ResourceNotFoundException;
import com.example.model.MedicalRecord;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/medicalrecords")
public class MedicalRecordResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordResource.class);
    private MedicalRecordDAOImpl medicalRecordDAO = new MedicalRecordDAOImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getAllMedicalRecords() {
        LOGGER.info("Request received: GET /mediacalrecord");
        return medicalRecordDAO.getAllMedicalRecords();
    }

    @GET
    @Path("/{medicalrecordId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MedicalRecord getMedicalRecordById(@PathParam("medicalrecordId") int medicalrecordId) {
        LOGGER.info("Request received: GET /mediacalrecord/{}", medicalrecordId);
        MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(medicalrecordId);
        if (medicalRecord != null) {
            return medicalRecord;
        } else {
            throw new ResourceNotFoundException("Medical Record with ID " + medicalrecordId + " not found.");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        LOGGER.info("Request received: POST /mediacalrecord - payload: {}", medicalRecord);
        medicalRecordDAO.addMedicalRecord(medicalRecord);
        return Response.status(Response.Status.CREATED).entity(medicalRecord + " Successfully Added").build();
    }

    @PUT
    @Path("/{medicalrecordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("medicalrecordId") int medicalrecordId, MedicalRecord updatedMedicalRecord) {
        LOGGER.info("Request received: PUT /mediacalrecord/{} - payload: {}", medicalrecordId, updatedMedicalRecord);
        MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecordById(medicalrecordId);

        if (existingMedicalRecord != null) {
            updatedMedicalRecord.setId(medicalrecordId);
            medicalRecordDAO.updateMedicalRecord(updatedMedicalRecord);
            return Response.status(Response.Status.OK).entity(medicalrecordId + " Successfully Updated").build();
        } else {
            throw new ResourceNotFoundException("Mediacal record not found.");
        }
    }

    @DELETE
    @Path("/{medicalrecordId}")
    public Response deleteMedicalRecord(@PathParam("medicalrecordId") int medicalrecordId) {
        LOGGER.info("Request received: DELETE /mediacalrecord/{}", medicalrecordId);
        MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecordById(medicalrecordId);
        if(existingMedicalRecord != null){
            medicalRecordDAO.deleteMedicalRecord(medicalrecordId);
            return  Response.status(Response.Status.OK).entity(existingMedicalRecord + " Successfully deleted").build();
        } else {
            throw new ResourceNotFoundException("Medical Record with ID " + medicalrecordId + " not found.");
        }
    }
}
