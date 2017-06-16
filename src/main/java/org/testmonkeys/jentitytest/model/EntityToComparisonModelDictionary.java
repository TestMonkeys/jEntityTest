package org.testmonkeys.jentitytest.repo;

import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.framework.JEntityTestException;
import org.testmonkeys.jentitytest.inspect.EntityInspector;

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
     * Gets the instance of <code>EntityToComparisonModelDictionary</code>
     * @return <code>EntityToComparisonModelDictionary</code>
     */
    public static synchronized EntityToComparisonModelDictionary getInstance() {
        if (instance == null)
            instance = new EntityToComparisonModelDictionary();
        return instance;
    }

    /**
     * Registers or overwrites Comparison Model for a given entity type
     *
     * @param clazz Entity class
     * @param model ComparisonModel
     */
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
