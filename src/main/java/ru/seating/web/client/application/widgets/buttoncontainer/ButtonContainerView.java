
package ru.seating.web.client.application.widgets.buttoncontainer;

import com.google.common.base.Preconditions;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;

class ButtonContainerView extends ViewImpl implements ButtonContainerPresenter.MyView {
    interface Binder extends UiBinder<Widget, ButtonContainerView> {
    }

    private HashMap<String, Button> buttonHashMap;

    @UiField
    HTMLPanel buttonsPanel;

    @Inject
    ButtonContainerView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
        buttonHashMap = new HashMap<>();
    }

    @Override
    public void clear() {
        buttonsPanel.clear();
        buttonHashMap.clear();
    }

    @Override
    public void add(@Nonnull UserButton userButton) {
        Preconditions.checkNotNull(userButton);
        Button button = new Button(userButton.getText());
        button.addClickHandler(userButton.getHandler());
        buttonHashMap.put(userButton.getId(), button);
        buttonsPanel.add(button);
    }

    @Override
    public void allAll(@Nonnull List<UserButton> userButtonList) {
        Preconditions.checkNotNull(userButtonList);
        for (UserButton userButton : userButtonList) {
            if (userButton != null) {
                add(userButton);
            }
        }
    }
}
