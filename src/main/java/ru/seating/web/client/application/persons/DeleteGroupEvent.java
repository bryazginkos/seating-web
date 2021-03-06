package ru.seating.web.client.application.persons;

import com.google.common.base.Preconditions;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import ru.seating.web.client.model.Group;

import javax.annotation.Nonnull;

/**
 * Created by Константин on 07.01.2015.
 */
public class DeleteGroupEvent extends GwtEvent<DeleteGroupEvent.DeleteGroupHandler> {

    public static Type<DeleteGroupHandler> TYPE = new Type<>();

    public interface DeleteGroupHandler extends EventHandler {
        void onDeleteGroup(DeleteGroupEvent deleteGroupEvent);
    }

    private Group group;

    public DeleteGroupEvent(@Nonnull Group group) {
        Preconditions.checkNotNull(group);
        this.group = group;
    }

    @Override
    protected void dispatch(DeleteGroupHandler deleteGroupHandler) {
        deleteGroupHandler.onDeleteGroup(this);
    }

    @Override
    public Type<DeleteGroupHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<DeleteGroupHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new DeleteGroupEvent(null));
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof DeleteGroupEvent)) return false;

        DeleteGroupEvent that = (DeleteGroupEvent) o;

        if (!group.equals(that.group)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return group.hashCode();
    }
}
