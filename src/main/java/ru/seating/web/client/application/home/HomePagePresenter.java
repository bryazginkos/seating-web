package ru.seating.web.client.application.home;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
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

public class HomePagePresenter extends Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy>
        implements HomeUIHandlers {
    public interface MyView extends View, HasUiHandlers<HomeUIHandlers> {
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
        getView().setUiHandlers(this);
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.MAIN_SLOT, this);
    }


    @Override
    public void onStartClick() {
        PlaceRequest placeRequest = new PlaceRequest(NameTokens.persons);
        placeManager.revealPlace(placeRequest);
    }

    @Override
    public void onContactsClick() {
        addToPopupSlot(contactsPresenter);
    }

    @Override
    public void onAboutClick() {
        addToPopupSlot(aboutPresenter);
    }
}
