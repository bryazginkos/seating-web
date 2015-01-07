
package ru.seating.web.client.application.persons.group;

import com.google.common.base.Preconditions;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import ru.seating.web.client.model.Group;

import javax.annotation.Nonnull;

class GroupView extends ViewWithUiHandlers<GroupUiHandlers> implements GroupPresenter.MyView {
    interface Binder extends UiBinder<Widget, GroupView> {
    }

    private final static String PATH = "images//";

    @UiField
    Image deleteIcon;

    @UiField
    Image groupIcon;

    @UiField
    Label groupName;

    @Inject
    GroupView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void showGroup(@Nonnull Group group) {
        Preconditions.checkNotNull(group);
        groupIcon.setUrl(PATH + group.getColor().getFilename());
        groupName.setText(group.getTitle());
    }

    @UiHandler("groupIcon")
    public void onGroupIconClick(ClickEvent clickEvent) {
        getUiHandlers().openEditWindow();
    }

    @UiHandler("groupName")
    public void onGroupNameClick(ClickEvent clickEvent) {
        getUiHandlers().openEditWindow();
    }

    @UiHandler("deleteIcon")
    public void onDeleteIconClick(ClickEvent clickEvent) {
        getUiHandlers().delete();
    }
}
