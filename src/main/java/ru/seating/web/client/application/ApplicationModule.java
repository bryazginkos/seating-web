package ru.seating.web.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import ru.seating.web.client.application.about.AboutModule;
import ru.seating.web.client.application.contacts.ContactsModule;
import ru.seating.web.client.application.home.HomeModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
        install(new AboutModule());
        install(new ContactsModule());
    }
}
