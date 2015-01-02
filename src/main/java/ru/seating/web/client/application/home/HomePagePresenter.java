package ru.seating.web.client.application.home;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import ru.seating.web.client.application.ApplicationPresenter;
import ru.seating.web.client.application.home.about.AboutPresenter;
import ru.seating.web.client.application.home.contacts.ContactsPresenter;
import ru.seating.web.client.place.NameTokens;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class HomePagePresenter extends Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy> {
    public interface MyView extends View {
        void addAboutClickHandler(@Nonnull ClickHandler handler);
        void addContactsClickHandler(@Nonnull ClickHandler handler);
        void addStartHandler(@Nonnull ClickHandler handler);
    }

    @Inject
    AboutPresenter aboutPresenter;

    @Inject
    ContactsPresenter contactsPresenter;

    @Inject
    PlaceManager placeManager;

    @ProxyStandard
    @NameToken(NameTokens.home)
    public interface MyProxy extends ProxyPlace<HomePagePresenter> {
    }

    @Inject
    HomePagePresenter(EventBus eventBus,
                      MyView view,
                      MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_SLOT);
        addHandlers();
    }

    private void addHandlers() {
        addAboutHandler();
        addContactsHandler();
        addStartHandler();
    }

    private void addAboutHandler() {
        getView().addAboutClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                addToPopupSlot(aboutPresenter);
            }
        });
    }

    private void addContactsHandler() {
        getView().addContactsClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                addToPopupSlot(contactsPresenter);
            }
        });
    }

    private void addStartHandler() {
        getView().addStartHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                goToFirstStep();
            }
        });
    }

    private void goToFirstStep() {
        PlaceRequest placeRequest = new PlaceRequest(NameTokens.persons);
        placeManager.revealPlace(placeRequest);
    }
}
