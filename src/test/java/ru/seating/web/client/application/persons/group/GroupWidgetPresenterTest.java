package ru.seating.web.client.application.persons.group;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.jukito.JukitoRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.seating.web.client.application.persons.DeleteGroupEvent;
import ru.seating.web.client.model.Group;
import ru.seating.web.client.model.GroupColor;
import ru.seating.web.client.utils.ExpectedEvent;

import static org.mockito.Mockito.*;

@RunWith(JukitoRunner.class)
public class GroupWidgetPresenterTest {
    private static final Group GROUP = new Group("test group", GroupColor.BLUE);

    @Inject
    private GroupPresenter groupPresenter;

    @Inject
    private EventBus eventBus;

    @Rule
    public ExpectedEvent expectedEvent = ExpectedEvent.none();

    @Test
    public void testShowGroup() {
        groupPresenter.showGroup(GROUP);
        verify(groupPresenter.getView()).showGroup(GROUP);
    }

    @Test
    public void testThrowDeleteEvent() {
        groupPresenter.showGroup(GROUP);
        expectedEvent.expect(new DeleteGroupEvent(GROUP), eventBus);
        groupPresenter.delete();
    }

}
