
package ru.seating.web.client.application.persons.editperson;

import com.google.common.base.Preconditions;
import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import ru.seating.web.client.application.messagebox.MessageBoxPresenter;
import ru.seating.web.client.application.persons.EditPersonEvent;
import ru.seating.web.client.application.persons.editperson.groupcheckbox.GroupCheckBoxPresenter;
import ru.seating.web.client.exception.BusinessException;
import ru.seating.web.client.model.ModelManager;
import ru.seating.web.client.model.Person;
import ru.seating.web.client.model.Sex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EditPersonPresenter extends PresenterWidget<EditPersonPresenter.MyView> implements EditPersonUiHandlers {
    interface MyView extends PopupView, HasUiHandlers<EditPersonUiHandlers> {
        @Nonnull Sex getSex();
        void setSex(@Nonnull Sex sex);

        boolean isSingle();
        void setSingle(boolean single);

        @Nonnull String getName();
        void setName(@Nonnull String name);
    }

    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> GROUPS_SLOT = new GwtEvent.Type<>();

    private Person person;

    private EventBus eventBus;

    @Inject
    private GroupCheckBoxPresenter groupCheckBoxPresenter;

    @Inject
    private MessageBoxPresenter messageBoxPresenter;

    @Inject
    EditPersonPresenter(final EventBus eventBus, final MyView view) {
        super(eventBus, view);
        this.eventBus = eventBus;

        getView().setUiHandlers(this);
    }

    public void initForEdit(@Nonnull Person person) {
        Preconditions.checkNotNull(person);
        this.person = person;
        groupCheckBoxPresenter.createCheckBoxes();
        groupCheckBoxPresenter.fillCheckBoxes(person.getGroupSet());
        getView().setName(person.getName());
        getView().setSingle(person.isSingle());
        getView().setSex(person.getSex());
    }

    public void initForCreating() {
        person = null;
        groupCheckBoxPresenter.createCheckBoxes();
        getView().setName("");
        getView().setSex(Sex.MALE);
        getView().setSingle(false);
    }

    @Override
    public void onClose() {
        getView().hide();
    }

    @Override
    public void onAdd() {
        if (validate()) {
            if (person == null) {
                Person candidate = createPerson();
                if (candidate != null) {
                    addPersonToModel(candidate);
                    getView().hide();
                }
            } else {
                refreshPerson();
            }
        }
    }

    private void addPersonToModel(@Nonnull Person candidate) {
        Preconditions.checkNotNull(candidate);
        try {
            ModelManager.getModel().addPerson(candidate);
            eventBus.fireEvent(new EditPersonEvent(candidate));
        } catch (BusinessException e) {
            messageBoxPresenter.configure("Error");
            addToPopupSlot(messageBoxPresenter);
        }
    }

    @Override
    protected void onBind() {
        super.onBind();
        setInSlot(GROUPS_SLOT, groupCheckBoxPresenter);
    }

    private boolean validate() {
        if (getView().getName().equals("")) {
            messageBoxPresenter.configure("Please enter Name field");
            addToPopupSlot(messageBoxPresenter);
            return false;
        }
        return true;
    }

    private void refreshPerson() {
        try {
            person.replaceGroups(groupCheckBoxPresenter.getCheckedGroups()); //throws
            person.setName(getView().getName());
            person.setSex(getView().getSex());
            person.setSingle(getView().isSingle());
            eventBus.fireEvent(new EditPersonEvent(person));
        } catch (BusinessException e) {
            messageBoxPresenter.configure("Group list has become not actual. Please fill groups one more time");
            groupCheckBoxPresenter.createCheckBoxes();
        }
    }

    @Nullable
    private Person createPerson() {
        Person candidate = new Person(getView().getName(), getView().isSingle(), getView().getSex());
        try {
            candidate.addGroups(groupCheckBoxPresenter.getCheckedGroups());
            return candidate;
        }
        catch (BusinessException e) {
            messageBoxPresenter.configure("Group list has become not actual. Please fill groups one more time");
            groupCheckBoxPresenter.createCheckBoxes();
        }
        return null;
    }
}
