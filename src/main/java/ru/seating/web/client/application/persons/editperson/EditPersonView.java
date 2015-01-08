
package ru.seating.web.client.application.persons.editperson;

import com.google.common.base.Preconditions;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;
import ru.seating.web.client.model.Sex;

import javax.annotation.Nonnull;

class EditPersonView extends PopupViewWithUiHandlers<EditPersonUiHandlers> implements EditPersonPresenter.MyView {
    interface Binder extends UiBinder<PopupPanel, EditPersonView> {
    }

    @UiField
    TextBox nameTextBox;

    @UiField
    HTMLPanel groupsArea;

    @UiField
    RadioButton sexM;

    @UiField
    RadioButton sexF;

    @UiField
    CheckBox singleCheckBox;

    @UiField
    Image closeIcon;

    @UiField
    Button addButton;

    @Inject
    EditPersonView(Binder uiBinder, EventBus eventBus) {
        super(eventBus);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public boolean isSingle() {
        return singleCheckBox.getValue();
    }

    @Override
    public void setSingle(boolean single) {
        singleCheckBox.setValue(single);
    }

    @Nonnull
    @Override
    public String getName() {
        return nameTextBox.getValue();
    }

    @Override
    public void setName(@Nonnull String name) {
        Preconditions.checkNotNull(name);
        nameTextBox.setValue(name);
    }

    @Override
    public void setSex(@Nonnull Sex sex) {
        sexM.setValue(Sex.MALE == sex);
        sexF.setValue(Sex.FEMALE == sex);
    }

    @Nonnull
    @Override
    public Sex getSex() {
        if (sexM.getValue()) {
            return Sex.MALE;
        } else {
            return Sex.FEMALE;
        }
    }

    @UiHandler("addButton")
    public void onAddButtonClick(ClickEvent event) {
        getUiHandlers().onAdd();
    }

    @UiHandler("closeIcon")
    public void onCloseIconClick(ClickEvent event) {
        getUiHandlers().onClose();
    }



    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == EditPersonPresenter.GROUPS_SLOT) {
            groupsArea.clear();
            if (content != null) {
                groupsArea.add(content);
            }
        }
        super.setInSlot(slot, content);
    }
}
