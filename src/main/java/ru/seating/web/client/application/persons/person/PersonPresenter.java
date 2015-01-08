
package ru.seating.web.client.application.persons.person;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import ru.seating.web.client.application.persons.DeletePersonEvent;
import ru.seating.web.client.application.persons.editperson.EditPersonPresenter;
import ru.seating.web.client.model.Person;

import javax.annotation.Nonnull;

public class PersonPresenter extends PresenterWidget<PersonPresenter.MyView> implements PersonUiHandlers {
    interface MyView extends View, HasUiHandlers<PersonUiHandlers> {
        void showPerson(@Nonnull Person person);
    }

    private Person person;

    private EventBus eventBus;

    @Inject
    private EditPersonPresenter editPersonPresenter;

    @Inject
    PersonPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);
        this.eventBus = eventBus;
        getView().setUiHandlers(this);
    }

    @Override
    public void delete() {
        DeletePersonEvent deletePersonEvent = new DeletePersonEvent(person);
        eventBus.fireEvent(deletePersonEvent);
    }

    @Override
    public void openEditWindow() {
        editPersonPresenter.initForEdit(person);
        addToPopupSlot(editPersonPresenter);
    }

    public void showPerson(@Nonnull Person person) {
        Preconditions.checkNotNull(person);
        this.person = person;
        getView().showPerson(person);
    }
}
