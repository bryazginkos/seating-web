package ru.seating.web.client.model;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;

/**
 * Created by Константин on 07.01.2015.
 */
public class Group {

    private String title;

    private GroupColor color;

    public Group(@Nonnull String title, @Nonnull GroupColor color) {
        Preconditions.checkNotNull(title);
        Preconditions.checkNotNull(color);
        this.title = title;
        this.color = color;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    @Nonnull
    public GroupColor getColor() {
        return color;
    }

    public void setTitle(@Nonnull String title) {
        Preconditions.checkNotNull(title);
        this.title = title;
    }

    public void setColor(@Nonnull GroupColor color) {
        Preconditions.checkNotNull(color);
        this.color = color;
    }
}
