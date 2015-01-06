package ru.seating.web.client.error;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

/**
 * Created by Константин on 06.01.2015.
 */
public class ErrorPageView extends ViewImpl implements ErrorPagePresenter.MyView {
    public interface Binder extends UiBinder<Widget, ErrorPageView> {
    }

    @Inject
    ErrorPageView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
