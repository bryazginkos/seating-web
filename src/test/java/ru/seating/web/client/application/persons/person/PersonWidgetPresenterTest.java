package ru.seating.web.client.application.persons.person;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.jukito.JukitoRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.seating.web.client.application.persons.DeletePersonEvent;
import ru.seating.web.client.model.Person;
import ru.seating.web.client.utils.ExpectedEvent;

import static org.mockito.Mockito.*;

@RunWith(JukitoRunner.class)
public class PersonWidgetPresenterTest {

    private static final Person PERSON = new Person("Kostya", false);

    @Inject
    private PersonPresenter personPresenter;

    @Inject
    private EventBus eventBus;

    @Rule
    public ExpectedEvent expectedEvent = ExpectedEvent.none();

    @Test
    public void testShowPerson() {
        personPresenter.showPerson(PERSON);
        verify(personPresenter.getView()).showPerson(PERSON);
    }

    @Test
    public void testThrowDeleteEvent() {
        personPresenter.showPerson(PERSON);
        expectedEvent.expect(new DeletePersonEvent(PERSON), eventBus);
        personPresenter.delete();
    }
}
