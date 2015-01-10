
package ru.seating.web.client.application.persons.editgroup;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import ru.seating.web.client.application.messagebox.MessageBoxPresenter;
import ru.seating.web.client.application.persons.EditGroupEvent;
import ru.seating.web.client.exception.BusinessException;
import ru.seating.web.client.model.Group;
import ru.seating.web.client.model.GroupColor;
import ru.seating.web.client.model.ModelManager;
import ru.seating.web.client.model.ModelUtils;

import javax.annotation.Nonnull;
import java.util.Collection;

public class EditGroupPresenter extends PresenterWidget<EditGroupPresenter.MyView> implements EditGroupUiHandlers {
    public interface MyView extends PopupView, HasUiHandlers<EditGroupUiHandlers> {
        void setColorBox(@Nonnull Collection<GroupColor> colors);
        void setGroupName(@Nonnull String name);
        void setGroupIcon(@Nonnull String fileName);
        @Nonnull String getGroupName();
        @Nonnull GroupColor getColor();
        void setColor(@Nonnull GroupColor color);
    }

    private Group group;

    private EventBus eventBus;

    @Inject
    private MessageBoxPresenter messageBoxPresenter;

    @Inject
    EditGroupPresenter(final EventBus eventBus, final MyView view) {
        super(eventBus, view);
        this.eventBus = eventBus;
        getView().setUiHandlers(this);
    }

    @Override
    public void onClose() {
        getView().hide();
    }

    @Override
    public void onSubmit() {
        if (validate()) {
            Group candidateGroup = null;
            if (group == null) {
                candidateGroup = new Group(getView().getGroupName(), getView().getColor());
                try {
                    ModelManager.getModel().addGroup(candidateGroup);
                    eventBus.fireEvent(new EditGroupEvent(group != null ? group : candidateGroup));
                    getView().hide();
                } catch (BusinessException e) {
                    messageBoxPresenter.configure("Can't add group");
                    getView().hide();
                    addToPopupSlot(messageBoxPresenter);
                }
            } else {
                group.setColor(getView().getColor());
                group.setTitle(getView().getGroupName());
                eventBus.fireEvent(new EditGroupEvent(group != null ? group : candidateGroup));
                getView().hide();
            }
        } else {
            messageBoxPresenter.configure("Please enter group name");
            addToPopupSlot(messageBoxPresenter);
        }
    }

    @Override
    public void onChangeGroupColor(GroupColor color) {
        getView().setGroupIcon(color.getFilename());
    }

    public void initForCreating() {
        group = null;
        Collection<GroupColor> freeColors = ModelUtils.getFreeColors();
        if (!freeColors.isEmpty()) {
            getView().setColorBox(freeColors);
            getView().setGroupName("");
        } else {
            getView().hide();
            showCantCreateGroup();
        }
    }

    public void initForEdit(@Nonnull Group group) {
        Preconditions.checkNotNull(group);
        this.group = group;
        Collection<GroupColor> freeColors = ModelUtils.getFreeColors();
        freeColors.add(group.getColor());
        getView().setColorBox(freeColors);
        getView().setColor(group.getColor());
        getView().setGroupName(group.getTitle());
    }

    private void showCantCreateGroup() {
        messageBoxPresenter.configure("Can't create group");
        addToPopupSlot(messageBoxPresenter);
    }

    private boolean validate() {
        return !getView().getGroupName().isEmpty();
    }
}
