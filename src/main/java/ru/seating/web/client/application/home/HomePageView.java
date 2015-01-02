package ru.seating.web.client.application.home;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.google.common.base.Preconditions;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomePageView extends ViewImpl implements HomePagePresenter.MyView {
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

    @Override
    public void addAboutClickHandler(@Nonnull ClickHandler handler) {
        Preconditions.checkNotNull(handler);
        aboutLink.addClickHandler(handler);
    }

    @Override
    public void addContactsClickHandler(@Nonnull ClickHandler handler) {
        Preconditions.checkNotNull(handler);
        contactsLink.addClickHandler(handler);
    }

    @Override
    public void addStartHandler(@Nonnull ClickHandler handler) {
        Preconditions.checkNotNull(handler);
        startButton.addClickHandler(handler);
        startLink.addClickHandler(handler);
    }
}
