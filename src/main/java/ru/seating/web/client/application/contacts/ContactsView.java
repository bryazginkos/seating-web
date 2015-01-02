
package ru.seating.web.client.application.contacts;

import com.google.common.base.Preconditions;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;

import javax.annotation.Nonnull;

class ContactsView extends PopupViewImpl implements ContactsPresenter.MyView {
    interface Binder extends UiBinder<PopupPanel, ContactsView> {
    }

    @UiField
    Button okButton;

    @Inject
    ContactsView(Binder uiBinder, EventBus eventBus) {
        super(eventBus);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void addCloseClickHandler(@Nonnull ClickHandler handler) {
        Preconditions.checkNotNull(handler);
        okButton.addClickHandler(handler);
    }
}
