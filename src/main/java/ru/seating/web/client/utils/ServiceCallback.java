package ru.seating.web.client.utils;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by Константин on 07.01.2015.
 */
public abstract class ServiceCallback<T> implements AsyncCallback<T> {
    @Override
    public void onFailure(Throwable throwable) {
        Window.alert(throwable.getMessage());
    }
}
