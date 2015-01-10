package ru.seating.web.client.application.persons.editgroup;

import com.gwtplatform.mvp.client.UiHandlers;
import ru.seating.web.client.model.GroupColor;

interface EditGroupUiHandlers extends UiHandlers {
    void onClose();
    void onSubmit();
    void onChangeGroupColor(GroupColor color);
}
