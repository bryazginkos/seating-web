package ru.seating.web.client.application.home;

import javax.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class HomePageView extends ViewWithUiHandlers<HomeUIHandlers> implements HomePagePresenter.MyView {
    public interface Binder extends UiBinder<Widget, HomePageView> {
    }

    @UiField Button startButton;
    @UiField Anchor startLink;
    @UiField Anchor contactsLink;
    @UiField Anchor aboutLink;

    @Inject
    HomePageView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("startButton")
    public void onStartClick(ClickEvent event) {
        if (getUiHandlers() != null) {
            getUiHandlers().onStartClick();
        }
    }

    @UiHandler("startLink")
    public void onStartLinkClick(ClickEvent event) {
        if (getUiHandlers() != null) {
            getUiHandlers().onStartClick();
        }
    }

    @UiHandler("contactsLink")
    public void onContactsClick(ClickEvent event) {
        if (getUiHandlers() != null) {
            getUiHandlers().onContactsClick();
        }
    }

    @UiHandler("aboutLink")
    public void onAboutClick(ClickEvent event) {
        if (getUiHandlers() != null) {
            getUiHandlers().onAboutClick();
        }
    }

}
