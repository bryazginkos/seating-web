package ru.seating.web.client.application.persons;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import ru.seating.web.client.application.persons.person.PersonPresenter;
import ru.seating.web.client.model.Model;
import ru.seating.web.client.model.ModelManager;
import ru.seating.web.client.model.Person;
import ru.seating.web.client.place.NameTokens;
import ru.seating.web.client.utils.ServiceCallback;

import javax.inject.Inject;

/**
 * Created by Константин on 02.01.2015.
 */
public class PersonPagePresenter extends Presenter<PersonPagePresenter.MyView, PersonPagePresenter.MyProxy>
        implements PersonPageUIHandlers {
    public interface MyView extends View, HasUiHandlers<PersonPageUIHandlers> {
    }

    private final DeletePersonEvent.DeletePersonHandler deletePersonHandler = new DeletePersonEvent.DeletePersonHandler() {
        @Override
        public void onDeletePerson(DeletePersonEvent deletePersonEvent) {
            ModelManager.getModel().deletePerson(deletePersonEvent.getPerson());
            configureByModel();
        }
    };

    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> PERSONS_SLOT = new GwtEvent.Type<>();

    @ProxyStandard
    @NameToken(NameTokens.persons)
    public interface MyProxy extends ProxyPlace<PersonPagePresenter> {
    }

    private IndirectProvider<PersonPresenter> personPresenterFactory;

    @Inject
    PersonPagePresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy,
                         Provider<PersonPresenter> personPresenterFactory) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_SLOT);
        this.personPresenterFactory = new StandardProvider<PersonPresenter>(personPresenterFactory);
        getView().setUiHandlers(this);
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.MAIN_SLOT, this);
    }

    @Override
    public void onAddGroupClick() {
        //todo
    }

    @Override
    public void onAddPersonClick() {
        //todo
    }

    @Override
    public void onContinueClick() {
        //todo
    }


    private void configureByModel() {
        Model model = ModelManager.getModel();
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

    @Override
    protected void onBind() {
        super.onBind();
        getEventBus().addHandler(DeletePersonEvent.getType(), deletePersonHandler);
    }

    @Override
    protected void onReset() {
        super.onReset();
        configureByModel();
    }
}
