
package ru.seating.web.client.application.persons.group;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class GroupModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenterWidget(GroupPresenter.class, GroupPresenter.MyView.class, GroupView.class);
    }
}
