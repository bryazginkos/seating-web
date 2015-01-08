
package ru.seating.web.client.application.messagebox;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;

public class MessageBoxPresenter extends PresenterWidget<MessageBoxPresenter.MyView> implements MessageBoxUiHandlers {
    interface MyView extends PopupView, HasUiHandlers<MessageBoxUiHandlers> {
        void setText(@Nonnull String text);
        void setButtons(@Nonnull Collection<MessageBoxButton> buttons);
    }

    private EventBus eventBus;

    @Inject
    MessageBoxPresenter(final EventBus eventBus, final MyView view) {
        super(eventBus, view);

        this.eventBus = eventBus;
        getView().setUiHandlers(this);
    }

    @Override
    public void onClick(@Nonnull MessageBoxButton messageBoxButton) {
        Preconditions.checkNotNull(messageBoxButton);
        if (messageBoxButton.getEvent() != null) {
            eventBus.fireEvent(messageBoxButton.getEvent());
        }
        getView().hide();
    }

    public void configure(@Nonnull String text, @Nonnull Collection<MessageBoxButton> messageBoxButtons) {
        Preconditions.checkNotNull(text);
        Preconditions.checkNotNull(messageBoxButtons);

        getView().setText(text);
        getView().setButtons(messageBoxButtons);
    }

    public void configure(@Nonnull String text) {
        Preconditions.checkNotNull(text);
        getView().setText(text);
        Collection<MessageBoxButton> messageBoxButtons = Arrays.asList(new MessageBoxButton("OK"));
        getView().setButtons(messageBoxButtons);
    }


}
