package ru.seating.web.client.application.home;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomePageView extends ViewImpl implements HomePagePresenter.MyView {
    public interface Binder extends UiBinder<Widget, HomePageView> {
    }

    @UiField Button startButton;
    @UiField Hyperlink startLink;
    @UiField Hyperlink contactsLink;
    @UiField Hyperlink aboutLink;

    @Inject
    HomePageView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
