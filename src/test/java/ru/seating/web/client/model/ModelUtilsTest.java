package ru.seating.web.client.model;

import org.junit.Before;
import org.junit.Test;
import ru.seating.web.client.exception.BusinessException;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class ModelUtilsTest {

    @Before
    public void prepareModel() throws BusinessException {
        ModelManager.clear();
        Model model = ModelManager.getModel();
        model.addGroup(new Group("Red group", GroupColor.RED));
    }

    @Test
    public void testFreeGroups() {
        Collection<GroupColor> freeColors = ModelUtils.getFreeColors();
        Collection<GroupColor> expectedFreeColor = Arrays.asList(GroupColor.BLUE, GroupColor.YELLOW);
        assertEquals(expectedFreeColor, freeColors);
    }

}
