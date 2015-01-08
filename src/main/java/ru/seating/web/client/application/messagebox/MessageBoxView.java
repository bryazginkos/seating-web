
package ru.seating.web.client.application.messagebox;

import com.google.common.base.Preconditions;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;

import javax.annotation.Nonnull;
import java.util.Collection;

class MessageBoxView extends PopupViewWithUiHandlers<MessageBoxUiHandlers> implements MessageBoxPresenter.MyView {
    interface Binder extends UiBinder<PopupPanel, MessageBoxView> {
    }

    @UiField
    Label textLabel;

    @UiField
    HTMLPanel buttonsPanel;

    @Inject
    MessageBoxView(Binder uiBinder, EventBus eventBus) {
        super(eventBus);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setButtons(@Nonnull Collection<MessageBoxButton> buttons) {
        Preconditions.checkNotNull(buttons);
        buttonsPanel.clear();
        for (final MessageBoxButton messageBoxButton : buttons) {
            Button button = new Button();
            button.setText(messageBoxButton.getText());
            button.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    getUiHandlers().onClick(messageBoxButton);
                }
            });
            buttonsPanel.add(button);
        }
    }

    @Override
    public void setText(@Nonnull String text) {
        Preconditions.checkNotNull(text);
        textLabel.setText(text);
    }
}
