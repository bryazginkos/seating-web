
package ru.seating.web.client.application.persons.editperson.groupcheckbox;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class GroupCheckBoxModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenterWidget(GroupCheckBoxPresenter.class, GroupCheckBoxPresenter.MyView.class, GroupCheckBoxView.class);
    }
}
