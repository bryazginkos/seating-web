package ru.seating.web.client.application.messagebox;

import com.gwtplatform.mvp.client.UiHandlers;

import javax.annotation.Nonnull;

interface MessageBoxUiHandlers extends UiHandlers {
    void onClick(@Nonnull MessageBoxButton messageBoxButton);
}
