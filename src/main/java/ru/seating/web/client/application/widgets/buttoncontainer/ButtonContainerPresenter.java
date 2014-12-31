
package ru.seating.web.client.application.widgets.buttoncontainer; 

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import javax.annotation.Nonnull;
import java.util.List;

public class ButtonContainerPresenter extends PresenterWidget<ButtonContainerPresenter.MyView> {
    interface MyView extends View {
        void clear();
        void add(@Nonnull UserButton userButton);
        void allAll(@Nonnull List<UserButton> userButtonList);
    }

    @Inject
    ButtonContainerPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);
    }


}
