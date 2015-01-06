package ru.seating.web.client.application.persons;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import ru.seating.web.client.application.ApplicationPresenter;
import ru.seating.web.client.place.NameTokens;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Created by Константин on 02.01.2015.
 */
public class PersonPagePresenter extends Presenter<PersonPagePresenter.MyView, PersonPagePresenter.MyProxy> {
    public interface MyView extends View {
        void addContinueHandler(@Nonnull ClickHandler handler);
        void addGuestHandler(@Nonnull ClickHandler handler);
        void addGroupHandler(@Nonnull ClickHandler handler);
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
        addHandlers();
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.MAIN_SLOT, this);
    }

    private void addHandlers() {
        getView().addContinueHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                nextStep();
            }
        });

        getView().addGroupHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                addGroup();
            }
        });

        getView().addGuestHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                addGuest();
            }
        });
    }

    private void nextStep() {

    }

    private void addGuest() {

    }

    private void addGroup() {

    }
}
