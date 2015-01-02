
package ru.seating.web.client.application.about;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;

public class AboutPresenter extends PresenterWidget<AboutPresenter.MyView> {
    interface MyView extends PopupView {
        void addCloseClickHandler(ClickHandler handler);
    }

    @Inject
    AboutPresenter(final EventBus eventBus, final MyView view) {
        super(eventBus, view);
        getView().addCloseClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                close();
            }
        });
    }

    private void close() {
        getView().hide();
    }


}
