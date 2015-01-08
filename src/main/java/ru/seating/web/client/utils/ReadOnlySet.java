package ru.seating.web.client.utils;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Константин on 08.01.2015.
 */
public class ReadOnlySet<T> implements Iterable<T>{

    private Set<T> set;

    public ReadOnlySet(@Nonnull Set<T> set) {
        Preconditions.checkNotNull(set);
        this.set = set;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ReadOnlySet)) return false;

        ReadOnlySet that = (ReadOnlySet) o;

        if (!set.equals(that.set)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }
}
