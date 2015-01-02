package ru.seating.web.client.application.home;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import ru.seating.web.client.application.ApplicationPresenter;
import ru.seating.web.client.application.about.AboutPresenter;
import ru.seating.web.client.place.NameTokens;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class HomePagePresenter extends Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy> {
    public interface MyView extends View {
        void addAboutClickHandler(ClickHandler handler);
    }

    @Inject
    AboutPresenter aboutPresenter;

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
    }

    private void addAboutHandler() {
        getView().addAboutClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                addToPopupSlot(aboutPresenter);
            }
        });
    }
}
