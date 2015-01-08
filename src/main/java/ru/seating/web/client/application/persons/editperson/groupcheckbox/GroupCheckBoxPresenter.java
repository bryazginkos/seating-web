
package ru.seating.web.client.application.persons.editperson.groupcheckbox; 

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import ru.seating.web.client.model.Group;
import ru.seating.web.client.model.ModelManager;
import ru.seating.web.client.utils.ReadOnlySet;

import javax.annotation.Nonnull;
import java.util.Collection;

public class GroupCheckBoxPresenter extends PresenterWidget<GroupCheckBoxPresenter.MyView> {
    interface MyView extends View {
        void createCheckBoxes(@Nonnull ReadOnlySet<Group> groups);
        @Nonnull Collection<Group> getCheckedGroups();
        void fillCheckBoxes(@Nonnull ReadOnlySet<Group> groups);
    }

    @Inject
    GroupCheckBoxPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);
    }

    /**
     * Create unfilled group checkboxes according to model group list.
     */
    public void createCheckBoxes() {
        getView().createCheckBoxes(ModelManager.getModel().getGroups());
    }

    /**
     * Fills created checkboxes
     * @param groups - checkboxes which belong to this groups will be checked
     */
    public void fillCheckBoxes(@Nonnull ReadOnlySet<Group> groups) {
        getView().fillCheckBoxes(groups);
    }

    /**
     * Returns collection of groups, which checkboxes were checked
     * @return
     */
    @Nonnull
    public Collection<Group> getCheckedGroups() {
        return getView().getCheckedGroups();
    }

}
