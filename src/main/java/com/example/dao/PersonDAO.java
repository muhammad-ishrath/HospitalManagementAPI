
package com.example.dao;


import com.example.model.Person;
import java.util.List;

public interface PersonDAO {
    List<Person> getAllPersons();
    Person getPersonById(int id);
    void addPerson(Person person);
    void updatePerson(Person updatedPerson);
    void deletePerson(int id);
    int getNextPersonId();
}
