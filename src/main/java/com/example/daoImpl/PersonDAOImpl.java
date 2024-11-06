
package com.example.daoImpl;

import com.example.dao.PersonDAO;
import com.example.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonDAOImpl implements PersonDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAOImpl.class);
    private static List<Person> persons = new ArrayList<>();

    public List<Person> getAllPersons() {
        LOGGER.info("Getting all persons");
        return persons;
    }

    @Override
    public Person getPersonById(int id) {
        LOGGER.info("Getting person with ID: {}", id);
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    @Override
    public void addPerson(Person person) {
         LOGGER.info("Adding person: {}", person);
        int newPersonId = getNextPersonId();
        person.setId(newPersonId);
        persons.add(person);
    }

    @Override
    public void updatePerson(Person updatedPerson) {
        LOGGER.info("Updating person: {}", updatedPerson);
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getId()== updatedPerson.getId()) {
                persons.set(i, updatedPerson);
                System.out.println("Person updated: " + updatedPerson);
                return;
            }
        }
    }

    @Override
    public void deletePerson(int id) {
        LOGGER.info("Deleting person with ID: {}", id);
        persons.removeIf(person -> person.getId() == id);
    }
    
    @Override
    public int getNextPersonId() {
        LOGGER.info("Getting next person ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxPersonId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Person person : persons) {
            int personId = person.getId();
            if (personId > maxPersonId) {
                maxPersonId = personId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxPersonId + 1;
    }
}
