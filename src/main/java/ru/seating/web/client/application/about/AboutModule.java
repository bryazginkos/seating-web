package ru.seating.web.client.application.about;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AboutModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindSingletonPresenterWidget(AboutPresenter.class, AboutPresenter.MyView.class, AboutView.class);
    }
}
