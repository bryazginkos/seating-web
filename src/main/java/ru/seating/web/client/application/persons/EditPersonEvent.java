package ru.seating.web.client.application.persons;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import ru.seating.web.client.model.Person;

public class EditPersonEvent extends GwtEvent<EditPersonEvent.EditPersonHandler> {
    public static GwtEvent.Type<EditPersonHandler> TYPE = new GwtEvent.Type<>();

    public interface EditPersonHandler extends EventHandler {
        void onEditPerson(EditPersonEvent EditPersonEvent);
    }

    private Person person;

    public EditPersonEvent(Person person) {
        this.person = person;
    }

    @Override
    public Type<EditPersonHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EditPersonHandler EditPersonHandler) {
        EditPersonHandler.onEditPerson(this);
    }

    public static Type<EditPersonHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new EditPersonEvent(null));
    }

    public Person getPerson() {
        return person;
    }
}
