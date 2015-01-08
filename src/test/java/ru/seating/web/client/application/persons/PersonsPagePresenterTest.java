package ru.seating.web.client.application.persons;

import com.google.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import ru.seating.web.client.application.persons.group.GroupPresenter;
import ru.seating.web.client.application.persons.person.PersonPresenter;
import ru.seating.web.client.exception.BusinessException;
import ru.seating.web.client.model.*;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.*;

@RunWith(JukitoRunner.class)
public class PersonsPagePresenterTest {

    private static final Group BLUE_GROUP = new Group("Blue group", GroupColor.BLUE);
    private static final Group YELLOW_GROUP = new Group("Yellow group", GroupColor.YELLOW);

    private Person PERSON_1;
    private Person PERSON_2;
    private Person PERSON_3;

    private Model model;

    @Inject
    PersonPagePresenter personPagePresenter;

    @Before
    public void prepare() throws BusinessException {
        ModelManager.clear();
        //magic for right mockito working
        model = spy(new Model());
        ModelManager.putMockModel(model);

        PERSON_1 = new Person("Person #1", true, Sex.FEMALE);
        PERSON_2 = new Person("Person #2", true, Sex.MALE);
        PERSON_3 = new Person("Person #3", false, Sex.FEMALE);

        HashSet<Group> groupHashSet = new HashSet<Group>() {{
            add(BLUE_GROUP);
            add(YELLOW_GROUP);
        }};

        HashSet<Person> personHashSet = new HashSet<Person>() {{
            add(PERSON_1);
            add(PERSON_2);
            add(PERSON_3);
        }};

        model.addGroups(groupHashSet);
        model.addPersons(personHashSet);

        PERSON_1.addGroup(BLUE_GROUP);
        PERSON_2.addGroups(Arrays.asList(BLUE_GROUP, YELLOW_GROUP));
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
    public void testDeletePerson() throws BusinessException {
        personPagePresenter.onDeletePerson(new DeletePersonEvent(PERSON_1));
        verify(model).deletePerson(PERSON_1);
    }

    @Test
    public void testDeleteGroup() throws BusinessException {
        personPagePresenter.onDeleteGroup(new DeleteGroupEvent(BLUE_GROUP));
        verify(model).deleteGroup(BLUE_GROUP);
    }
}
