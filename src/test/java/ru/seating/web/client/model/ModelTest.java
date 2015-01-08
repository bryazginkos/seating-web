package ru.seating.web.client.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.seating.web.client.exception.BusinessException;
import ru.seating.web.client.utils.ReadOnlySet;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class ModelTest {

    private static final Group BLUE_GROUP = new Group("Blue group", GroupColor.BLUE);
    private static final Group YELLOW_GROUP = new Group("Yellow group", GroupColor.YELLOW);

    private Person PERSON_1;

    private Person PERSON_2;

    private Person PERSON_3;

    @Before
    public void prepare() throws BusinessException {
        ModelManager.clear();
        Model model = ModelManager.getModel();
        model.addGroups(Arrays.asList(BLUE_GROUP, YELLOW_GROUP));

        PERSON_1 = new Person("Person #1", true, Sex.FEMALE);
        PERSON_2 = new Person("Person #2", true, Sex.MALE);
        PERSON_3 = new Person("Person #3", false,  Sex.FEMALE);
        model.addPersons(Arrays.asList(PERSON_1, PERSON_2, PERSON_3));

        PERSON_1.addGroup(BLUE_GROUP);
        PERSON_2.addGroups(Arrays.asList(BLUE_GROUP, YELLOW_GROUP));
    }

    @Test
    public void testDeletePerson() throws BusinessException {
        ModelManager.getModel().deletePerson(PERSON_1);
        Model actualModel = ModelManager.getModel();
        Model expectedModel = createModelWithoutPerson1();
        Assert.assertEquals(expectedModel, actualModel);
    }

    @Test(expected = BusinessException.class)
    public void testDeletePersonWhenNoSuchPerson() throws BusinessException {
        ModelManager.getModel().deletePerson(new Person("Peter", false, Sex.FEMALE));
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteNullPerson() throws BusinessException {
        ModelManager.getModel().deletePerson(null);
    }

    @Test
    public void testDeleteGroup() throws BusinessException {
        ModelManager.getModel().deleteGroup(BLUE_GROUP);
        Model actualModel = ModelManager.getModel();

        HashSet<Group> expectedGroups = new HashSet<>(Arrays.asList(YELLOW_GROUP));
        assertEquals(new ReadOnlySet<>(expectedGroups), actualModel.getGroups());

        HashSet<Person> expectedPersons = new HashSet<>(Arrays.asList(PERSON_1, PERSON_2, PERSON_3));
        assertEquals(new ReadOnlySet<>(expectedPersons), actualModel.getPersons());

        assertTrue(PERSON_1.getGroupSet().isEmpty());
        assertTrue(PERSON_3.getGroupSet().isEmpty());

        HashSet<Group> expectedPerson2Groups = new HashSet<>(Arrays.asList(YELLOW_GROUP));
        assertEquals(new ReadOnlySet<>(expectedPerson2Groups), PERSON_2.getGroupSet());
    }

    @Test(expected = BusinessException.class)
    public void testDeleteGroupWhenNoSuchGroup() throws BusinessException {
        ModelManager.getModel().deleteGroup(new Group("red group", GroupColor.RED));
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteNullGroup() throws BusinessException {
        ModelManager.getModel().deleteGroup(null);
    }

    private Model createModelWithoutPerson1() throws BusinessException {
        Model model = new Model();
        model.addGroups(Arrays.asList(BLUE_GROUP, YELLOW_GROUP));
        model.addPersons(Arrays.asList(PERSON_2, PERSON_3));
        return model;
    }
}
