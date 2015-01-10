
package ru.seating.web.client.application.persons.group; 

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import ru.seating.web.client.application.persons.DeleteGroupEvent;
import ru.seating.web.client.application.persons.editgroup.EditGroupPresenter;
import ru.seating.web.client.model.Group;

import javax.annotation.Nonnull;

public class GroupPresenter extends PresenterWidget<GroupPresenter.MyView> implements GroupUiHandlers {
    interface MyView extends View, HasUiHandlers<GroupUiHandlers> {
        void showGroup(@Nonnull Group group);
    }

    private Group group;

    private EventBus eventBus;

    @Inject
    private EditGroupPresenter editGroupPresenter;

    @Inject
    GroupPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);
        this.eventBus = eventBus;

        getView().setUiHandlers(this);
    }

    @Override
    public void delete() {
        DeleteGroupEvent deleteGroupEvent = new DeleteGroupEvent(group);
        eventBus.fireEvent(deleteGroupEvent);
    }

    @Override
    public void openEditWindow() {
        editGroupPresenter.initForEdit(group);
        addToPopupSlot(editGroupPresenter);
    }

    public void showGroup(@Nonnull Group group) {
        Preconditions.checkNotNull(group);
        this.group = group;
        getView().showGroup(group);
    }
}
