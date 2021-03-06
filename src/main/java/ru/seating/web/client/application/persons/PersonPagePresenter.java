package ru.seating.web.client.application.persons;

import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import ru.seating.web.client.application.ApplicationPresenter;
import ru.seating.web.client.application.messagebox.MessageBoxPresenter;
import ru.seating.web.client.application.persons.editgroup.EditGroupPresenter;
import ru.seating.web.client.application.persons.editperson.EditPersonPresenter;
import ru.seating.web.client.application.persons.group.GroupPresenter;
import ru.seating.web.client.application.persons.person.PersonPresenter;
import ru.seating.web.client.exception.BusinessException;
import ru.seating.web.client.model.*;
import ru.seating.web.client.place.NameTokens;
import ru.seating.web.client.utils.ServiceCallback;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Константин on 02.01.2015.
 */
public class PersonPagePresenter extends Presenter<PersonPagePresenter.MyView, PersonPagePresenter.MyProxy>
        implements PersonPageUIHandlers, DeletePersonEvent.DeletePersonHandler, DeleteGroupEvent.DeleteGroupHandler, EditPersonEvent.EditPersonHandler, EditGroupEvent.EditGroupHandler {
    public interface MyView extends View, HasUiHandlers<PersonPageUIHandlers> {
    }

    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> PERSONS_SLOT = new GwtEvent.Type<>();

    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> GROUPS_SLOT = new GwtEvent.Type<>();

    @ProxyStandard
    @NameToken(NameTokens.persons)
    public interface MyProxy extends ProxyPlace<PersonPagePresenter> {
    }

    private IndirectProvider<PersonPresenter> personPresenterFactory;

    private IndirectProvider<GroupPresenter> groupPresenterFactory;

    @Inject
    private MessageBoxPresenter messageBoxPresenter;

    @Inject
    private EditPersonPresenter editPersonPresenter;

    @Inject
    private EditGroupPresenter editGroupPresenter;

    @Inject
    PersonPagePresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy,
                         Provider<PersonPresenter> personPresenterFactory,
                         Provider<GroupPresenter> groupPresenterFactory) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_SLOT);
        this.personPresenterFactory = new StandardProvider<PersonPresenter>(personPresenterFactory);
        this.groupPresenterFactory = new StandardProvider<GroupPresenter>(groupPresenterFactory);
        getView().setUiHandlers(this);
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.MAIN_SLOT, this);
    }

    @Override
    public void onAddGroupClick() {
        Collection<GroupColor> freeColors = ModelUtils.getFreeColors();
        if (!freeColors.isEmpty()) {
            editGroupPresenter.initForCreating();
            addToPopupSlot(editGroupPresenter);
        } else {
            messageBoxPresenter.configure("Can't create one more group");
            addToPopupSlot(messageBoxPresenter);
        }
    }

    @Override
    public void onAddPersonClick() {
        editPersonPresenter.initForCreating();
        addToPopupSlot(editPersonPresenter);
    }

    @Override
    public void onContinueClick() {
        //todo
    }

    @Override
    public void onDeletePerson(DeletePersonEvent deletePersonEvent) {
        try {
            ModelManager.getModel().deletePerson(deletePersonEvent.getPerson());
        } catch (BusinessException e) {
            //todo move text to one place
            messageBoxPresenter.configure("Error while deleting person. Probably the person was deleted before.");
            addToPopupSlot(messageBoxPresenter);
        }
        configureByModel();
    }

    @Override
    public void onEditPerson(EditPersonEvent EditPersonEvent) {
        configureByModel();
    }

    @Override
    public void onEditGroup(EditGroupEvent EditGroupEvent) {
        configureByModel();
    }

    @Override
    public void onDeleteGroup(DeleteGroupEvent deleteGroupEvent) {
        try {
            ModelManager.getModel().deleteGroup(deleteGroupEvent.getGroup());
        } catch (BusinessException e) {
            //todo move text to one place
            messageBoxPresenter.configure("Error while deleting group. Probably the group was deleted before.");
            addToPopupSlot(messageBoxPresenter);
        }
        configureByModel();
    }

    private void configureByModel() {
        Model model = ModelManager.getModel();
        configurePersons(model);
        configureGroups(model);
    }

    private void configurePersons(@Nonnull Model model) {
        setInSlot(PERSONS_SLOT, null);
        if (model.getPersons() != null) {
            for (final Person person : model.getPersons()) {
                personPresenterFactory.get(new ServiceCallback<PersonPresenter>() {
                    @Override
                    public void onSuccess(PersonPresenter personPresenter) {
                        addToSlot(PERSONS_SLOT, personPresenter);
                        personPresenter.showPerson(person);
                    }
                });
            }
        }
    }

    private void configureGroups(@Nonnull Model model) {
        setInSlot(GROUPS_SLOT, null);
        if (model.getGroups() != null) {
            for (final Group group : model.getGroups()) {
                groupPresenterFactory.get(new ServiceCallback<GroupPresenter>() {
                    @Override
                    public void onSuccess(GroupPresenter groupPresenter) {
                        addToSlot(GROUPS_SLOT, groupPresenter);
                        groupPresenter.showGroup(group);
                    }
                });
            }
        }

    }

    @Override
    protected void onBind() {
        super.onBind();
        getEventBus().addHandler(DeletePersonEvent.getType(), this);
        getEventBus().addHandler(DeleteGroupEvent.getType(), this);
        getEventBus().addHandler(EditPersonEvent.getType(), this);
        getEventBus().addHandler(EditGroupEvent.getType(), this);
    }

    @Override
    protected void onReset() {
        super.onReset();
        configureByModel();
    }
}
