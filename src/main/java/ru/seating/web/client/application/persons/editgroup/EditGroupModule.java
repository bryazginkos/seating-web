package ru.seating.web.client.application.persons.editgroup;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class EditGroupModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindSingletonPresenterWidget(EditGroupPresenter.class, EditGroupPresenter.MyView.class, EditGroupView.class);
    }
}
