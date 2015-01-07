package ru.seating.web.client.model;

/**
 * Created by Константин on 07.01.2015.
 */
public enum GroupColor {

    RED("red.png"),
    BLUE("blue.png"),
    YELLOW("yellow.png");

    private String filename;

    GroupColor(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
