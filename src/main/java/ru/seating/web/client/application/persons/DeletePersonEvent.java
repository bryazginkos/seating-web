package ru.seating.web.client.application.persons;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import ru.seating.web.client.model.Person;

/**
 * Created by Константин on 07.01.2015.
 */
public class DeletePersonEvent extends GwtEvent<DeletePersonEvent.DeletePersonHandler> {

    public static GwtEvent.Type<DeletePersonHandler> TYPE = new GwtEvent.Type<>();

    public interface DeletePersonHandler extends EventHandler {
        void onDeletePerson(DeletePersonEvent deletePersonEvent);
    }

    private Person person;

    public DeletePersonEvent(Person person) {
        this.person = person;
    }

    @Override
    public Type<DeletePersonHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DeletePersonHandler deletePersonHandler) {
        deletePersonHandler.onDeletePerson(this);
    }

    public static Type<DeletePersonHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new DeletePersonEvent(null));
    }

    public Person getPerson() {
        return person;
    }
}
