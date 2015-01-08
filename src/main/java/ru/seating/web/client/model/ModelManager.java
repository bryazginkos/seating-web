package ru.seating.web.client.model;

import javax.annotation.Nonnull;

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
        }
        return model;
    }

    /**
     * Resets Model Object
     */
    public static void clear() {
        model = null;
    }

    /**
     * Puts model mock for tests
     * @param mockModel
     */
    public static void putMockModel(Model mockModel) {
        model = mockModel;
    }
}
