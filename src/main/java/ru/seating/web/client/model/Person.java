package ru.seating.web.client.model;

import com.google.common.base.Preconditions;
import ru.seating.web.client.utils.ReadOnlySet;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Константин on 07.01.2015.
 */
public class Person {
    private String name;

    private HashMap<Person, Integer> relations;

    private Set<Group> groupSet;

    private boolean isSingle;

    public Person(@Nonnull String name, boolean isSingle) {
        Preconditions.checkNotNull(name);
        this.name = name;
        this.isSingle = isSingle;
        groupSet = new HashSet<>();
        relations = new HashMap<>();
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        Preconditions.checkNotNull(name);
        this.name = name;
    }

    @Nonnull
    public HashMap<Person, Integer> getRelations() {
        return relations;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }

    /**
     * Returns set of groups of this Person <br/>
     * read only!
     * @return
     */
    @Nonnull
    public ReadOnlySet<Group> getGroupSet() {
        return new ReadOnlySet<>(groupSet);
    }

    public void removeGroup(@Nonnull Group group) {
        Preconditions.checkNotNull(group);
        if (!groupSet.contains(group)) {
            throw new IllegalArgumentException("Can't delete unknown group " + group.getTitle() + " from person " + name);
        }
        groupSet.remove(group);
    }

    public void addGroup(@Nonnull Group group) {
        Preconditions.checkNotNull(group);
        if (!ModelManager.getModel().getGroups().contains(group)) {
            throw new IllegalArgumentException("Cannot add unknown group " + group.getTitle() + " to person " + name);
        }
        if (groupSet.contains(group)) {
            throw new IllegalArgumentException("Person " + name + " already belongs to group " + group.getTitle());
        }
        groupSet.add(group);
    }

    public void addGroups(@Nonnull Collection<Group> groups) {
        Preconditions.checkNotNull(groups);
        for (Group group : groups) {
            addGroup(group);
        }
    }

    public void replaceGroups(@Nonnull Collection<Group> groups) {
        Preconditions.checkNotNull(groups);
        groupSet.clear();
        addGroups(groups);
    }
}
