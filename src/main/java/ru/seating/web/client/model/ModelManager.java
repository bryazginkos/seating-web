package ru.seating.web.client.model;

/**
 * Keeps Model object
 */
public class ModelManager {
    private static Model model;

    /**
     * Returns Model Object
     * @return
     */
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
}
