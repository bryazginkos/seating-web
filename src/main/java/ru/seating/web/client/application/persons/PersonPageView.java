package ru.seating.web.client.application.persons;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import javax.inject.Inject;

/**
 * Created by Константин on 02.01.2015.
 */
public class PersonPageView extends ViewWithUiHandlers<PersonPageUIHandlers> implements PersonPagePresenter.MyView {
    public interface Binder extends UiBinder<Widget, PersonPageView> {
    }

    @UiField Button addGroupButton;

    @UiField Button addGuestButton;

    @UiField Button continueButton;

    @UiField HTMLPanel groupsPanel;

    @UiField HTMLPanel personsPanel;

    @Inject
    PersonPageView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("addGroupButton")
         public void onAddGroupClick(ClickEvent clickEvent) {
        getUiHandlers().onAddGroupClick();
    }

    @UiHandler("addGuestButton")
    public void onAddPersonClick(ClickEvent clickEvent) {
        getUiHandlers().onAddPersonClick();
    }

    @UiHandler("continueButton")
    public void onContinueClick(ClickEvent clickEvent) {
        getUiHandlers().onContinueClick();
    }
}
