package ru.seating.web.client.model;

import java.util.HashMap;

/**
 * Created by Константин on 07.01.2015.
 */
public class Person {
    private String name;

    private HashMap<Person, Integer> relations;

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
}
