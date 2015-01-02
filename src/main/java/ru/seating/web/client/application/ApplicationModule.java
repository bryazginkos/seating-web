package ru.seating.web.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import ru.seating.web.client.application.home.HomeModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}
