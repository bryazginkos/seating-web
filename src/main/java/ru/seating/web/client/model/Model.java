package ru.seating.web.client.model;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

/**
 * Model object. (persons with relations between them)
 */
public class Model {
    private Set<Person> persons;
    private Set<Group> groups;

    public Model() {
        persons = new HashSet<>();
        groups = new HashSet<>();
    }

    public void deletePerson(@Nonnull Person person) throws IllegalArgumentException {
        Preconditions.checkNotNull(person);
        if (!persons.remove(person)) {
            throw new IllegalArgumentException("Person " + person.getName() + " is not found");
        }
    }

    public void deleteGroup(@Nonnull Group group) throws IllegalArgumentException {
        Preconditions.checkNotNull(group);
        if (!groups.remove(group)) {
            throw new IllegalArgumentException("Group " + group.getTitle() + " is not found");
        }
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

    public Set<Group> getGroups() {
        return groups;
    }

}
