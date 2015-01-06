package ru.seating.web.client.model;

import java.util.HashMap;

/**
 * Model object. (Hashmap of persons with relations between them)
 */
public class Model {
    private HashMap<Integer, Person> persons;

    public HashMap<Integer, Person> getPersons() {
        return persons;
    }

    public void setPersons(HashMap<Integer, Person> persons) {
        this.persons = persons;
    }
}
