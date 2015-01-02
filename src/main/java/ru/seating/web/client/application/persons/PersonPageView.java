package ru.seating.web.client.application.persons;

import com.google.common.base.Preconditions;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Created by Константин on 02.01.2015.
 */
public class PersonPageView extends ViewImpl implements PersonPagePresenter.MyView {
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

    @Override
    public void addContinueHandler(@Nonnull ClickHandler handler) {
        Preconditions.checkNotNull(handler);
        continueButton.addClickHandler(handler);
    }

    @Override
    public void addGuestHandler(@Nonnull ClickHandler handler) {
        Preconditions.checkNotNull(handler);
        addGuestButton.addClickHandler(handler);

    }

    @Override
    public void addGroupHandler(@Nonnull ClickHandler handler) {
        Preconditions.checkNotNull(handler);
        addGroupButton.addClickHandler(handler);
    }
}
