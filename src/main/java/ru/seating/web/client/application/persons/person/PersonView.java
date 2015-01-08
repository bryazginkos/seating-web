
package ru.seating.web.client.application.persons.person;

import com.google.common.base.Preconditions;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import ru.seating.web.client.model.Group;
import ru.seating.web.client.model.Person;
import ru.seating.web.client.utils.ReadOnlySet;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.function.Consumer;

class PersonView extends ViewWithUiHandlers<PersonUiHandlers> implements PersonPresenter.MyView {
    interface Binder extends UiBinder<Widget, PersonView> {
    }

    private static final String PATH = "images\\";

    @UiField
    HTMLPanel groupImages;

    @UiField
    Label nameLabel;

    @UiField
    Image closeIcon;

    @Inject
    PersonView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void showPerson(@Nonnull Person person) {
        Preconditions.checkNotNull(person);
        nameLabel.setText(person.getName());
        configureGroupImages(person.getGroupSet());
    }

    private void configureGroupImages(@Nonnull ReadOnlySet<Group> groupSet) {
        Preconditions.checkNotNull(groupSet);
        groupImages.clear();
        for (Group group : groupSet) {
            addImageToGroupImages(group);
        }
    }

    private void addImageToGroupImages(@Nonnull Group group) {
        Preconditions.checkNotNull(group);
        Image image = new Image();
        image.setUrl(PATH + group.getColor().getFilename());
        groupImages.add(image);
    }

    @UiHandler("nameLabel")
         public void onNameLabelClick(ClickEvent event) {
        getUiHandlers().openEditWindow();
    }

    @UiHandler("closeIcon")
    public void onGroupImagesClick(ClickEvent event) {
        getUiHandlers().delete();
    }
}
