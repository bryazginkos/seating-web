package ru.seating.web.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import ru.seating.web.client.application.home.HomeModule;
import ru.seating.web.client.application.home.about.AboutModule;
import ru.seating.web.client.application.home.contacts.ContactsModule;
import ru.seating.web.client.application.messagebox.MessageBoxModule;
import ru.seating.web.client.application.persons.PersonPageModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());
        install(new AboutModule());
        install(new ContactsModule());

        install(new PersonPageModule());

        install(new MessageBoxModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);

    }
}
