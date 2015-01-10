package ru.seating.web.client.application.persons;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import ru.seating.web.client.application.persons.editgroup.EditGroupModule;
import ru.seating.web.client.application.persons.editperson.EditPersonModule;
import ru.seating.web.client.application.persons.group.GroupModule;
import ru.seating.web.client.application.persons.person.PersonModule;

/**
 * Created by Константин on 02.01.2015.
 */
public class PersonPageModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(PersonPagePresenter.class, PersonPagePresenter.MyView.class, PersonPageView.class,
                PersonPagePresenter.MyProxy.class);
        install(new PersonModule());
        install(new GroupModule());
        install(new EditPersonModule());
        install(new EditGroupModule());
    }
}
