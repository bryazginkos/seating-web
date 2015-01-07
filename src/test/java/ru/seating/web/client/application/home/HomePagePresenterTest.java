package ru.seating.web.client.application.home;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.seating.web.client.place.NameTokens;

import static org.mockito.Mockito.*;

@RunWith(JukitoRunner.class)
public class HomePagePresenterTest {
    @Inject
    HomePagePresenter homePagePresenter;

    @Inject
    PlaceManager placeManager;

    @Test
    public void testGoToFirstStep() {
        homePagePresenter.onStartClick();
        PlaceRequest expectedRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.persons)
                .build();
        verify(placeManager).revealPlace(expectedRequest);
    }
}
