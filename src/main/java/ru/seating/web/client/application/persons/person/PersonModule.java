
package ru.seating.web.client.application.persons.person;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class PersonModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenterWidget(PersonPresenter.class, PersonPresenter.MyView.class, PersonView.class);
    }
}
