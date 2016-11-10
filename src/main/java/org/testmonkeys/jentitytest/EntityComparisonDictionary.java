package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.framework.JEntityTestException;
import org.testmonkeys.jentitytest.inspect.EntityInspector;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

public class EntityComparisonDictionary {
    private static EntityComparisonDictionary instance;
    private final Map<Class, ComparisonModel> dictionary;
    private final EntityInspector inspector;

    private EntityComparisonDictionary() {
        this.dictionary = new HashMap<>();
        this.inspector = new EntityInspector();
    }

    public static synchronized EntityComparisonDictionary getInstance() {
        if (EntityComparisonDictionary.instance == null)
            EntityComparisonDictionary.instance = new EntityComparisonDictionary();
        return EntityComparisonDictionary.instance;
    }

    public void addComparisonModel(Class clazz, ComparisonModel model) {
        this.dictionary.put(clazz, model);
    }

    public ComparisonModel getComparisonModel(Class clazz) throws JEntityTestException {
        if (!this.dictionary.containsKey(clazz)) {
            try {
                this.dictionary.put(clazz, this.inspector.getComparisonModel(clazz));
            } catch (IntrospectionException e) {
                throw new JEntityTestException("Could not create comparison model for class");
            }

        }
        return this.dictionary.get(clazz);
    }
}
