package ru.seating.web.client.model;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Константин on 07.01.2015.
 */
public class Person {
    private String name;

    private HashMap<Person, Integer> relations;

    private Set<Group> groupSet;

    private boolean isSingle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Person, Integer> getRelations() {
        return relations;
    }

    public void setRelations(HashMap<Person, Integer> relations) {
        this.relations = relations;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }

    /**
     * Set of groups which this person is belong to
     * @return
     */
    public Set<Group> getGroupSet() {
        return groupSet;
    }

    public void setGroupSet(Set<Group> groupSet) {
        this.groupSet = groupSet;
    }
}
