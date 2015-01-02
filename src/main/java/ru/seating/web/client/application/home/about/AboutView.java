
package ru.seating.web.client.application.home.about;

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

class AboutView extends PopupViewImpl implements AboutPresenter.MyView {
    interface Binder extends UiBinder<PopupPanel, AboutView> {
    }

    @UiField
    Button okButton;

    @Inject
    AboutView(Binder uiBinder, EventBus eventBus) {
        super(eventBus);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void addCloseClickHandler(@Nonnull ClickHandler handler) {
        Preconditions.checkNotNull(handler);
        okButton.addClickHandler(handler);
    }
}
