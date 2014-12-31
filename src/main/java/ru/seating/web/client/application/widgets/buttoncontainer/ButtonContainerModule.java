
package ru.seating.web.client.application.widgets.buttoncontainer;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ButtonContainerModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        //todo перенести в ApplicationModule
        bindPresenterWidget(ButtonContainerPresenter.class, ButtonContainerPresenter.MyView.class, ButtonContainerView.class);
    }
}
