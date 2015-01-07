package ru.seating.web.client.application.persons;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.jukito.JukitoRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import ru.seating.web.client.application.persons.group.GroupPresenter;
import ru.seating.web.client.application.persons.person.PersonPresenter;
import ru.seating.web.client.model.*;

import java.util.HashSet;

import static org.mockito.Mockito.*;

@RunWith(JukitoRunner.class)
public class PersonsPagePresenterTest {

    private static final Group BLUE_GROUP = new Group("Blue group", GroupColor.BLUE);
    private static final Group YELLOW_GROUP = new Group("Yellow group", GroupColor.YELLOW);

    private static final Person PERSON_1 = new Person() {{
        this.setGroupSet(new HashSet<Group>() {{
            add(BLUE_GROUP);
        }});
        this.setSingle(true);
        this.setName("Person #1");
    }};

    private static final Person PERSON_2 = new Person() {{
        this.setGroupSet(new HashSet<Group>() {{
            add(BLUE_GROUP);
            add(YELLOW_GROUP);
        }});
        this.setSingle(true);
        this.setName("Person #2");
    }};

    private static final Person PERSON_3 = new Person() {{
        this.setGroupSet(new HashSet<Group>() {{
        }});
        this.setSingle(false);
        this.setName("Person #3");
    }};

    @Inject
    PersonPagePresenter personPagePresenter;

    @Inject
    EventBus eventBus;

    @Before
    public void prepareModel() {
        Model model = ModelManager.getModel();
        model.setGroupSet(new HashSet<Group>(){{
            add(BLUE_GROUP);
            add(YELLOW_GROUP);
        }});
        model.setPersons(new HashSet<Person>(){{
            add(PERSON_1);
            add(PERSON_2);
            add(PERSON_3);
        }});
    }

    @Test
    public void testShowGroups(PersonPagePresenter.MyView myView, GroupPresenter groupPresenter) {
        personPagePresenter.onReset();
        InOrder inOrder = inOrder(myView);
        inOrder.verify(myView).setInSlot(PersonPagePresenter.GROUPS_SLOT, null);
        inOrder.verify(myView, times(2)).addToSlot(PersonPagePresenter.GROUPS_SLOT, groupPresenter);
    }

    @Test
    public void testShowPersons(PersonPagePresenter.MyView myView, PersonPresenter personPresenter) {
        personPagePresenter.onReset();
        InOrder inOrder = inOrder(myView);
        inOrder.verify(myView).setInSlot(PersonPagePresenter.PERSONS_SLOT, null);
        inOrder.verify(myView, times(3)).addToSlot(PersonPagePresenter.PERSONS_SLOT, personPresenter);
    }

    @Test
    public void testDeletePerson() {
        eventBus.fireEvent(new DeletePersonEvent(PERSON_1));
        Model actualModel = ModelManager.getModel();
        Model expectedModel = createModelWithoutPerson1();
        Assert.assertEquals(expectedModel, actualModel);
    }

    private Model createModelWithoutPerson1() {
        Model model = ModelManager.getModel();
        model.setGroupSet(new HashSet<Group>(){{
            add(BLUE_GROUP);
            add(YELLOW_GROUP);
        }});
        model.setPersons(new HashSet<Person>(){{
            add(PERSON_2);
            add(PERSON_3);
        }});
        return model;
    }
}
