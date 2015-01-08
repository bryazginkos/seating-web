package ru.seating.web.client.application.messagebox;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class MessageBoxModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenterWidget(MessageBoxPresenter.class, MessageBoxPresenter.MyView.class, MessageBoxView.class);
    }
}
