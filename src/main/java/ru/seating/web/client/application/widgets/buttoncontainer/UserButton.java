package ru.seating.web.client.application.widgets.buttoncontainer;

import com.google.common.base.Preconditions;
import com.google.gwt.event.dom.client.ClickHandler;

import javax.annotation.Nonnull;

/**
 * Created with IntelliJ IDEA.
 * User: Константин
 * Date: 31.12.14
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public class UserButton {

    private String id;

    private String text;

    private ClickHandler handler;

    /**
     * creates userButton for ButtonContainer
     * @param id
     * @param text
     * @param handler
     */
    public UserButton(@Nonnull String id, @Nonnull String text, @Nonnull ClickHandler handler) {
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(text);
        Preconditions.checkNotNull(handler);

        this.id = id;
        this.text = text;
        this.handler = handler;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public ClickHandler getHandler() {
        return handler;
    }
}
