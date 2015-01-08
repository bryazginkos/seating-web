package ru.seating.web.client.model;

import com.google.common.base.Preconditions;
import ru.seating.web.client.exception.BusinessException;
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


    //1 - man, 0 - woman
    private Sex sex;

    public Person(@Nonnull String name, boolean isSingle, @Nonnull Sex sex) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(sex);
        this.name = name;
        this.isSingle = isSingle;
        this.sex = sex;
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
    public Sex getSex() {
        return sex;
    }

    public void setSex(@Nonnull Sex sex) {
        Preconditions.checkNotNull(sex);
        this.sex = sex;
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

    public void removeGroup(@Nonnull Group group) throws BusinessException {
        Preconditions.checkNotNull(group);
        if (!groupSet.contains(group)) {
            throw new BusinessException("Can't delete unknown group " + group.getTitle() + " from person " + name);
        }
        groupSet.remove(group);
    }

    public void addGroup(@Nonnull Group group) throws BusinessException {
        Preconditions.checkNotNull(group);
        if (!ModelManager.getModel().getGroups().contains(group)) {
            throw new BusinessException("Cannot add unknown group " + group.getTitle() + " to person " + name);
        }
        if (groupSet.contains(group)) {
            throw new BusinessException("Person " + name + " already belongs to group " + group.getTitle());
        }
        groupSet.add(group);
    }

    public void addGroups(@Nonnull Collection<Group> groups) throws BusinessException {
        Preconditions.checkNotNull(groups);
        for (Group group : groups) {
            addGroup(group);
        }
    }

    public void replaceGroups(@Nonnull Collection<Group> groups) throws BusinessException {
        Preconditions.checkNotNull(groups);
        groupSet.clear();
        addGroups(groups);
    }
}
