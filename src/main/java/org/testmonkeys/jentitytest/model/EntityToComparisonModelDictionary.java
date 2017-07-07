package org.testmonkeys.jentitytest.model;

import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton Comparison Model dictionary for all parsed entities.
 */
public final class EntityToComparisonModelDictionary {
    private static EntityToComparisonModelDictionary instance;
    private final Map<Class, ComparisonModel> dictionary;
    private final EntityInspector inspector;

    private EntityToComparisonModelDictionary() {
        dictionary = new HashMap<>();
        inspector = new EntityInspector();
    }

    /**
     * Gets the instance of {@code EntityToComparisonModelDictionary}
     * @return {@code EntityToComparisonModelDictionary}
     */
    public static synchronized EntityToComparisonModelDictionary getInstance() {
        if (instance == null)
            instance = new EntityToComparisonModelDictionary();
        return instance;
    }

    /**
     * Registers or overwrites Comparison Model for a given strategies type
     *
     * @param clazz Entity class
     * @param model ComparisonModel
     */
    @SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
    public void addComparisonModel(Class clazz, ComparisonModel model) {
        dictionary.put(clazz, model);
    }

    /**
     * Gets the comparison model for a given Entity class
     *
     * @param clazz Entity class
     * @return ComparisonModel
     * @throws JEntityTestException in the event that the class can not be inspected for generating the ComparisonModel
     */
    public ComparisonModel getComparisonModel(Class clazz) {
        if (!dictionary.containsKey(clazz)) {
            dictionary.put(clazz, inspector.getComparisonModel(clazz));
        }
        return dictionary.get(clazz);
    }
}
