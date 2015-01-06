package ru.seating.web.client.application.persons;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import ru.seating.web.client.application.ApplicationPresenter;
import ru.seating.web.client.place.NameTokens;
import javax.inject.Inject;

/**
 * Created by Константин on 02.01.2015.
 */
public class PersonPagePresenter extends Presenter<PersonPagePresenter.MyView, PersonPagePresenter.MyProxy>
        implements PersonPageUIHandlers {
    public interface MyView extends View, HasUiHandlers<PersonPageUIHandlers> {
    }

    @ProxyStandard
    @NameToken(NameTokens.persons)
    public interface MyProxy extends Proxy<PersonPagePresenter> {
    }

    @Inject
    PersonPagePresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_SLOT);
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
}
