package ru.seating.web.client.error;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * Created by Константин on 06.01.2015.
 */
public class ErrorModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(ErrorPagePresenter.class, ErrorPagePresenter.MyView.class, ErrorPageView.class,
                ErrorPagePresenter.MyProxy.class);
    }

}
