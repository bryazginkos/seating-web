package ru.seating.web.client.model;

/**
 * Created by Константин on 07.01.2015.
 */
public class Group {

    private String title;

    private GroupColor color;

    public Group(String title, GroupColor color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public GroupColor getColor() {
        return color;
    }
}
