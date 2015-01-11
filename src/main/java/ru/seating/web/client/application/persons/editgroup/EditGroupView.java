
package ru.seating.web.client.application.persons.editgroup;

import com.google.common.base.Preconditions;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;
import ru.seating.web.client.model.GroupColor;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Collection;

class EditGroupView extends PopupViewWithUiHandlers<EditGroupUiHandlers> implements EditGroupPresenter.MyView {
    interface Binder extends UiBinder<PopupPanel, EditGroupView> {
    }

    private final static String PATH = "images//";

    @UiField
    TextBox groupNameTextBox;

    @UiField
    ValueListBox<GroupColor> colorBox;

    @UiField
    Image colorIcon;

    @UiField
    Button okButton;

    @UiField
    Image closeIcon;

    @Inject
    EditGroupView(Binder uiBinder, EventBus eventBus) {
        super(eventBus);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiFactory
    ValueListBox<GroupColor> createValueListBox() {
        return new ValueListBox<GroupColor>(new Renderer<GroupColor>() {
            @Override
            public String render(GroupColor color) {
                return color != null ? color.getFilename() : ""; //todo fix
            }

            @Override
            public void render(GroupColor color, Appendable appendable) throws IOException {
                appendable.append(color.getFilename());//todo fix
            }
        });
    }

    @UiHandler("colorBox")
    public void onGroupColorChange(ValueChangeEvent<GroupColor> valueChangeEvent){
        getUiHandlers().onChangeGroupColor(valueChangeEvent.getValue());
    }

    @UiHandler("okButton")
    public void onOkClick(ClickEvent event) {
        getUiHandlers().onSubmit();
    }

    @UiHandler("closeIcon")
    public void onCloseClick(ClickEvent event) {
        getUiHandlers().onClose();
    }

    @Override
    public void setColorBox(@Nonnull Collection<GroupColor> colors) {
        Preconditions.checkNotNull(colors);
        Preconditions.checkArgument(!colors.isEmpty());
        colorBox.setAcceptableValues(colors);

        GroupColor first = colors.iterator().next();
        colorBox.setValue(first);
        colorBox.setAcceptableValues(colors); //not double, bug hack!
        getUiHandlers().onChangeGroupColor(first);
    }

    @Override
    public void setGroupName(@Nonnull String name) {
        Preconditions.checkNotNull(name);
        groupNameTextBox.setValue(name);
    }

    @Nonnull
    @Override
    public String getGroupName() {
        return groupNameTextBox.getValue();
    }

    @Nonnull
    @Override
    public GroupColor getColor() {
        return colorBox.getValue();
    }

    @Override
    public void setColor(@Nonnull GroupColor color) {
        Preconditions.checkNotNull(color);
        colorBox.setValue(color);
        getUiHandlers().onChangeGroupColor(color);
    }

    @Override
    public void setGroupIcon(@Nonnull String fileName) {
        Preconditions.checkNotNull(fileName);
        colorIcon.setUrl(PATH + fileName);
    }
}
