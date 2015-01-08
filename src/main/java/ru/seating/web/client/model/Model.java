package ru.seating.web.client.model;

import com.google.common.base.Preconditions;
import ru.seating.web.client.exception.BusinessException;
import ru.seating.web.client.utils.ReadOnlySet;

import javax.annotation.Nonnull;
import java.util.Collection;
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

    public void deletePerson(@Nonnull Person person) {
        Preconditions.checkNotNull(person);
        if (!persons.remove(person)) {
            throw new IllegalArgumentException("Person " + person.getName() + " is not found");
        }
    }

    public void deleteGroup(@Nonnull Group group) {
        Preconditions.checkNotNull(group);
        if (!groups.remove(group)) {
            throw new IllegalArgumentException("Group " + group.getTitle() + " is not found");
        }
        if (persons != null) {
            for (Person person : persons) {
                try {
                    person.removeGroup(group);
                } catch (IllegalArgumentException ignore) {

                }
            }
        }
    }

    public void addGroup(@Nonnull Group group) throws BusinessException {
        Preconditions.checkNotNull(group);
        if (groups.contains(group)) {
            throw new IllegalArgumentException("Group " + group.getTitle() + " already exists");
        }
        for (Group existGroup : groups) {
            if (existGroup.getColor() == group.getColor()) {
                throw new IllegalArgumentException("Group with color " + group.getColor() + " already exists");
            }
            if (existGroup.getTitle().equals(group.getTitle())) {
                throw new BusinessException("Group with title " + group.getTitle() + " already exists");
            }
        }
        groups.add(group);
    }

    public void addGroups(@Nonnull Collection<Group> groupCollection) throws BusinessException {
        Preconditions.checkNotNull(groupCollection);
        for (Group group : groupCollection) {
            addGroup(group);
        }
    }

    public void addPerson(@Nonnull Person person) throws BusinessException {
        Preconditions.checkNotNull(person);
        if (persons.contains(person)) {
            throw new IllegalArgumentException("Person " + person.getName() + " already exists");
        }
        for (Person existPerson : persons) {
            if (existPerson.getName().equals(person.getName())) {
                throw new BusinessException("Person with name " + person.getName() + " already exists");
            }
        }
        for (Group personGroup : person.getGroupSet()) {
            if (!groups.contains(personGroup)) {
                throw new IllegalArgumentException("Person " + person.getName() + " belongs to unknown group " + personGroup.getTitle());
            }
        }
        if (!person.getRelations().isEmpty()) {
            throw new IllegalArgumentException("Person " + person.getName() + " has relations on adding to model step");
        }
        persons.add(person);
    }

    public void addPersons(@Nonnull Collection<Person> personCollection) throws BusinessException {
        Preconditions.checkNotNull(personCollection);
        for (Person person : personCollection) {
            addPerson(person);
        }
    }

    @Nonnull
    public ReadOnlySet<Person> getPersons() {
        return new ReadOnlySet<>(persons);
    }

    @Nonnull
    public ReadOnlySet<Group> getGroups() {
        return new ReadOnlySet<>(groups);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Model)) return false;

        Model model = (Model) o;

        if (!groups.equals(model.groups)) return false;
        if (!persons.equals(model.persons)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = persons.hashCode();
        result = 31 * result + groups.hashCode();
        return result;
    }
}
