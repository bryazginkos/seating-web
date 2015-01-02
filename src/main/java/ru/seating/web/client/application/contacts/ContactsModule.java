package ru.seating.web.client.application.contacts;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ContactsModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindSingletonPresenterWidget(ContactsPresenter.class, ContactsPresenter.MyView.class, ContactsView.class);
    }
}
