
package ru.seating.web.client.application.persons.editperson.groupcheckbox;

import com.google.common.base.Preconditions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import ru.seating.web.client.application.persons.editperson.groupcheckbox.widget.CheckBoxWidget;
import ru.seating.web.client.model.Group;
import ru.seating.web.client.utils.ReadOnlySet;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class GroupCheckBoxView extends ViewImpl implements GroupCheckBoxPresenter.MyView {
    interface Binder extends UiBinder<Widget, GroupCheckBoxView> {
    }

    private static final String PATH = "images/";

    @UiField
    HTMLPanel checkBoxPanel;

    private List<CheckBoxWidget> checkBoxWidgets;

    @Inject
    GroupCheckBoxView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Nonnull
    @Override
    public Collection<Group> getCheckedGroups() {
        Collection<Group> checked = new ArrayList<>();
        if (checkBoxWidgets != null) {
            for (CheckBoxWidget checkBoxWidget : checkBoxWidgets) {
                if (checkBoxWidget.isChecked()) {
                    checked.add(checkBoxWidget.getGroup());
                }
            }
        }
        return checked;
    }

    @Override
    public void createCheckBoxes(@Nonnull ReadOnlySet<Group> groups) {
        Preconditions.checkNotNull(groups);
        checkBoxPanel.clear();

        checkBoxWidgets = new ArrayList<>(groups.size());
        for (Group group : groups) {
            CheckBoxWidget checkBoxWidget = new CheckBoxWidget();
            checkBoxWidget.setGroup(group);
            checkBoxWidgets.add(checkBoxWidget);
            checkBoxPanel.add(checkBoxWidget);
        }
    }

    @Override
    public void fillCheckBoxes(@Nonnull ReadOnlySet<Group> groups) {
        Preconditions.checkNotNull(groups);
        if (checkBoxWidgets != null) {
            for (CheckBoxWidget checkBoxWidget : checkBoxWidgets) {
                boolean needCheck = groups.contains(checkBoxWidget.getGroup());
                checkBoxWidget.setChecked(needCheck);
            }
        }
    }
}
