package ru.seating.web.client.model;

import javax.annotation.Nonnull;
import java.util.HashSet;

/**
 * Keeps Model object
 */
public class ModelManager {
    private static Model model;

    /**
     * Returns Model Object
     * @return
     */
    @Nonnull
    public static Model getModel() {
        if (model == null) {
            model = new Model();
//            model.setPersons(new HashSet<Person>() {{
//                add(new Person(){{
//                    this.setName("Test");
//                    this.setSingle(true);
//                    this.setGroupSet(new HashSet<Group>() {{
//                        this.add(new Group("first group", GroupColor.BLUE));
//                    }});
//                }});
//            }});
//            model.setGroupSet(new HashSet<Group>() {{
//                add(new Group("first group", GroupColor.RED));
//                add(new Group("second group", GroupColor.BLUE));
//            }});
        }
        return model;
    }

    /**
     * Resets Model Object
     */
    public static void clear() {
        model = null;
    }
}
