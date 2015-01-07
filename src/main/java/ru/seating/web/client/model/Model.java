package ru.seating.web.client.model;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Model object. (persons with relations between them)
 */
public class Model {
    private Set<Person> persons;
    private Set<Group> groupSet;

    public void deletePerson(@Nonnull Person person) {
        Preconditions.checkNotNull(person);
        persons.remove(person);
    }

    public void deleteGroup(@Nonnull Group group) {
        Preconditions.checkNotNull(group);
        groupSet.remove(group);
        if (persons != null) {
            for (Person person : persons) {
                if (person.getGroupSet() != null) {
                    person.getGroupSet().remove(group);
                }
            }
        }
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Group> getGroupSet() {
        return groupSet;
    }

    public void setGroupSet(Set<Group> groupSet) {
        this.groupSet = groupSet;
    }
}
