package ru.seating.web.client.application.persons.editperson.groupcheckbox.widget;

import com.google.common.base.Preconditions;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import ru.seating.web.client.model.Group;

import javax.annotation.Nonnull;

/**
 * Created by Константин on 08.01.2015.
 */
public class CheckBoxWidget extends Composite {
    interface CheckBoxWidgetUiBinder extends UiBinder<HTMLPanel, CheckBoxWidget> {
    }

    private static String PATH = "images//";

    private Group group;

    @UiField
    CheckBox checkBox;

    @UiField
    Image colorIcon;

    private static CheckBoxWidgetUiBinder ourUiBinder = GWT.create(CheckBoxWidgetUiBinder.class);

    public CheckBoxWidget() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setGroup(@Nonnull Group group) {
        Preconditions.checkNotNull(group);
        this.group = group;
        checkBox.setText(group.getTitle());
        colorIcon.setUrl(PATH + group.getColor().getFilename());
    }

    public Group getGroup() {
        return group;
    }

    public boolean isChecked() {
        return checkBox.getValue();
    }

    public void setChecked(boolean value) {
        checkBox.setValue(value);
    }
}