package ru.seating.web.client.application.persons.group;

import com.gwtplatform.mvp.client.UiHandlers;

interface GroupUiHandlers extends UiHandlers {
    /**
     * Delete group
     */
    void delete();

    /**
     * Open window for edit group
     */
    void openEditWindow();
}
