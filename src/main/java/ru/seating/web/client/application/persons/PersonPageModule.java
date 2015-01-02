package ru.seating.web.client.application.persons;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * Created by Константин on 02.01.2015.
 */
public class PersonPageModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(PersonPagePresenter.class, PersonPagePresenter.MyView.class, PersonPageView.class,
                PersonPagePresenter.MyProxy.class);
    }
}
