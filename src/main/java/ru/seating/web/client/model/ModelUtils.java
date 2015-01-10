package ru.seating.web.client.model;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;

public class ModelUtils {
    @Nonnull
    public static Collection<GroupColor> getFreeColors() {
        Collection<GroupColor> freeColors = new ArrayList<>();
        for (GroupColor groupColor : GroupColor.values()) {
            boolean free = true;
            for (Group existGroup : ModelManager.getModel().getGroups()) {
                if (existGroup.getColor() == groupColor) {
                    free = false;
                }
            }
            if (free) {
                freeColors.add(groupColor);
            }
        }
        return freeColors;
    }
}
