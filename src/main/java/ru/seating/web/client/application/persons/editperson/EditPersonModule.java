package ru.seating.web.client.application.persons.editperson;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import ru.seating.web.client.application.persons.editperson.groupcheckbox.GroupCheckBoxModule;

public class EditPersonModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindSingletonPresenterWidget(EditPersonPresenter.class, EditPersonPresenter.MyView.class, EditPersonView.class);
        install(new GroupCheckBoxModule());
    }
}
