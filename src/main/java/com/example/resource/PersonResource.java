
package com.example.resource;

import com.example.daoImpl.PersonDAOImpl;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Person;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Path("/persons")
public class PersonResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    private PersonDAOImpl personDAO = new PersonDAOImpl();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
        LOGGER.info("Request received: GET /persons");
        return personDAO.getAllPersons();
    }

    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonById(@PathParam("personId") int personId) {
        LOGGER.info("Request received: GET /persons/{}", personId);
        Person person = personDAO.getPersonById(personId);
        if (person != null) {
            return person;
        } else {
            throw new ResourceNotFoundException("Person with ID " + personId + " not found.");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        LOGGER.info("Request received: POST /persons - payload: {}", person);
        personDAO.addPerson(person);
        return Response.status(Response.Status.CREATED).entity(person + " Successfully Added").build();
    }

    @PUT
    @Path("/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("personId") int personId, Person updatedPerson) {
         LOGGER.info("Request received: PUT /persons/{} - payload: {}", personId, updatedPerson);
        Person existingPerson = personDAO.getPersonById(personId);

        if (existingPerson != null) {
            updatedPerson.setId(personId);
            personDAO.updatePerson(updatedPerson);
            return Response.status(Response.Status.OK).entity(personId + " Successfully Updated").build();
        } else {
            throw new ResourceNotFoundException("Person not found.");
        }
    }

    @DELETE
    @Path("/{personId}")
    public Response deletePerson(@PathParam("personId") int personId) {
        LOGGER.info("Request received: DELETE /persons/{}", personId);
        Person existingPerson = personDAO.getPersonById(personId);
        if(existingPerson != null){
            personDAO.deletePerson(personId);
            return  Response.status(Response.Status.OK).entity(existingPerson + " Successfully deleted").build();
        } else {
            throw new ResourceNotFoundException("Person with ID " + personId + " not found.");
        }
    }
    
}

