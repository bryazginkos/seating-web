package ru.seating.web.client.application.messagebox;

import com.google.web.bindery.event.shared.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Константин on 08.01.2015.
 */
public class MessageBoxButton {
    private String text;
    private Event event;

    public MessageBoxButton(@Nonnull String text, @Nullable Event event) {
        this.text = text;
        this.event = event;
    }

    public MessageBoxButton(String text) {
        this.text = text;
    }

    @Nonnull
    public String getText() {
        return text;
    }

    @Nullable
    public Event getEvent() {
        return event;
    }
}
