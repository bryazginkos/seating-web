package ru.seating.web.client.error;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import ru.seating.web.client.place.NameTokens;

import javax.inject.Inject;

/**
 * Created by Константин on 06.01.2015.
 */
public class ErrorPagePresenter extends Presenter<ErrorPagePresenter.MyView, ErrorPagePresenter.MyProxy> {
    public interface MyView extends View {
    }

    @ProxyStandard
    @NameToken(NameTokens.error)
    public interface MyProxy extends ProxyPlace<ErrorPagePresenter> {
    }

    @Inject
    ErrorPagePresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }
}
