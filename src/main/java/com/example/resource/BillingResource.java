
package com.example.resource;

import com.example.daoImpl.BillingDAOImpl;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Billing;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/billings")
public class BillingResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingResource.class);
    private BillingDAOImpl billingDAO = new BillingDAOImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Billing> getAllBillings() {
        return billingDAO.getAllBillings();
    }

    @GET
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Billing getBillingById(@PathParam("billingId") int billingId) {
        LOGGER.info("Request received: GET /billings/{}", billingId);
        Billing billing = billingDAO.getBillingById(billingId);
        if (billing != null) {
            return billing;
        } else {
            throw new ResourceNotFoundException("Billing with ID " + billingId + " not found.");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        LOGGER.info("Request received: POST /billings - payload: {}", billing);
        billingDAO.addBilling(billing);
        return Response.status(Response.Status.CREATED).entity(billing + " Successfully Added").build();
    }

    @PUT
    @Path("/{billingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("billingId") int billingId, Billing updatedBilling) {
        LOGGER.info("Request received: PUT /billings/{} - payload: {}", billingId, updatedBilling);
        Billing existingBiling = billingDAO.getBillingById(billingId);

        if (existingBiling != null) {
            updatedBilling.setId(billingId);
            billingDAO.updateBilling(updatedBilling);
            return Response.status(Response.Status.OK).entity(billingId + " Successfully Updated").build();
        } else {
            throw new ResourceNotFoundException("Billing not found.");
        }
    }

    @DELETE
    @Path("/{billingId}")
    public Response deleteBilling(@PathParam("billingId") int billingId) {
        LOGGER.info("Request received: DELETE /billings/{}", billingId);
        Billing existingBilling = billingDAO.getBillingById(billingId);
        if(existingBilling != null) {
            billingDAO.deleteBilling(billingId);
            return  Response.status(Response.Status.OK).entity(existingBilling + " Successfully deleted").build();
        } else {
           throw new ResourceNotFoundException("Billing with ID " + billingId + " not found.");
        }
    }
}
