package ru.seating.web.client.application.persons;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import ru.seating.web.client.model.Group;

public class EditGroupEvent extends GwtEvent<EditGroupEvent.EditGroupHandler> {
    public static GwtEvent.Type<EditGroupHandler> TYPE = new GwtEvent.Type<>();

    public interface EditGroupHandler extends EventHandler {
        void onEditGroup(EditGroupEvent EditGroupEvent);
    }

    private Group group;

    public EditGroupEvent(Group group) {
        this.group = group;
    }

    @Override
    public Type<EditGroupHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EditGroupHandler EditGroupHandler) {
        EditGroupHandler.onEditGroup(this);
    }

    public static Type<EditGroupHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new EditGroupEvent(null));
    }

    public Group getGroup() {
        return group;
    }
}
