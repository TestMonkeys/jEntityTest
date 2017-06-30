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
        this.dictionary = new HashMap<>();
        this.inspector = new EntityInspector();
    }

    /**
     * Gets the instance of <code>EntityToComparisonModelDictionary</code>
     * @return <code>EntityToComparisonModelDictionary</code>
     */
    public static synchronized EntityToComparisonModelDictionary getInstance() {
        if (EntityToComparisonModelDictionary.instance == null)
            EntityToComparisonModelDictionary.instance = new EntityToComparisonModelDictionary();
        return EntityToComparisonModelDictionary.instance;
    }

    /**
     * Registers or overwrites Comparison Model for a given strategies type
     *
     * @param clazz Entity class
     * @param model ComparisonModel
     */
    public void addComparisonModel(Class clazz, ComparisonModel model) {
        this.dictionary.put(clazz, model);
    }

    /**
     * Gets the comparison model for a given Entity class
     *
     * @param clazz Entity class
     * @return ComparisonModel
     * @throws JEntityTestException in the event that the class can not be inspected for generating the ComparisonModel
     */
    public ComparisonModel getComparisonModel(Class clazz) {
        if (!this.dictionary.containsKey(clazz)) {
            this.dictionary.put(clazz, this.inspector.getComparisonModel(clazz));
        }
        return this.dictionary.get(clazz);
    }
}
