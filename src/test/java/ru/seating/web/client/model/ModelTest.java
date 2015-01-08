package ru.seating.web.client.model;

import com.google.common.base.Preconditions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashSet;

public class ModelTest {

    private static final Group BLUE_GROUP = new Group("Blue group", GroupColor.BLUE);
    private static final Group YELLOW_GROUP = new Group("Yellow group", GroupColor.YELLOW);

    private Person PERSON_1;

    private Person PERSON_2;

    private Person PERSON_3;

    @Before
    public void prepare() {
        preparePersons();
        prepareModel();
    }

    private void prepareModel() {
        ModelManager.clear();
        Model model = ModelManager.getModel();
        model.getGroups().addAll(Arrays.asList(BLUE_GROUP, YELLOW_GROUP));
        model.getPersons().addAll(Arrays.asList(PERSON_1, PERSON_2, PERSON_3));
    }

    private void preparePersons() {
        PERSON_1 = new Person() {{
            this.setGroupSet(new HashSet<Group>() {{
                add(BLUE_GROUP);
            }});
            this.setSingle(true);
            this.setName("Person #1");
        }};

        PERSON_2 = new Person() {{
            this.setGroupSet(new HashSet<Group>() {{
                add(BLUE_GROUP);
                add(YELLOW_GROUP);
            }});
            this.setSingle(true);
            this.setName("Person #2");
        }};

        PERSON_3 = new Person() {{
            this.setGroupSet(new HashSet<Group>() {{
            }});
            this.setSingle(false);
            this.setName("Person #3");
        }};
    }

    @Test
    public void testDeletePerson() {
        ModelManager.getModel().deletePerson(PERSON_1);
        Model actualModel = ModelManager.getModel();
        Model expectedModel = createModelWithoutPerson1();
        Assert.assertEquals(expectedModel, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeletePersonWhenNoSuchPerson() {
        ModelManager.getModel().deletePerson(new Person());
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteNullPerson() {
        ModelManager.getModel().deletePerson(null);
    }

    @Test
    public void testDeleteGroup() {
        ModelManager.getModel().deleteGroup(BLUE_GROUP);
        Model actualModel = ModelManager.getModel();
        Model expectedModel = createModelWithoutBlueGroup();
        Assert.assertEquals(expectedModel, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteGroupWhenNoSuchGroup() {
        ModelManager.getModel().deleteGroup(new Group("red group", GroupColor.RED));
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteNullGroup() {
        ModelManager.getModel().deleteGroup(null);
    }

    private Model createModelWithoutPerson1() {
        Model model = ModelManager.getModel();
        model.getGroups().addAll(Arrays.asList(BLUE_GROUP, YELLOW_GROUP));
        model.getPersons().addAll(Arrays.asList(PERSON_2, PERSON_3));
        return model;
    }

    private Model createModelWithoutBlueGroup() {
        deleteGroupFromPerson(BLUE_GROUP, PERSON_1);
        deleteGroupFromPerson(BLUE_GROUP, PERSON_2);
        Model model = ModelManager.getModel();
        model.getGroups().add(YELLOW_GROUP);
        model.getPersons().addAll(Arrays.asList(PERSON_1, PERSON_2, PERSON_3));
        return model;
    }

    private void deleteGroupFromPerson(@Nonnull Group group, @Nonnull Person person) {
        Preconditions.checkNotNull(person);
        Preconditions.checkNotNull(group);
        if (person.getGroupSet() != null) {
            person.getGroupSet().remove(group);
        }
    }
}
