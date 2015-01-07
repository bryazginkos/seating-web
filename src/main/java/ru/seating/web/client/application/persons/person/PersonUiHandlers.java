package ru.seating.web.client.application.persons.person;

import com.gwtplatform.mvp.client.UiHandlers;

interface PersonUiHandlers extends UiHandlers {
    /**
     * Delete person
     */
    void delete();

    /**
     * Open window for edit person
     */
    void openEditWindow();
}
