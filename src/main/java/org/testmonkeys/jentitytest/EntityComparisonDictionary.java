package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.inspect.EntityInspector;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

public class EntityComparisonDictionary {
    private static EntityComparisonDictionary instance;
    private final Map<Class, ComparisonModel> dictionary;
    private final EntityInspector inspector;

    private EntityComparisonDictionary() {
        dictionary = new HashMap<>();
        inspector = new EntityInspector();
    }

    public static synchronized EntityComparisonDictionary getInstance() {
        if (instance == null)
            instance = new EntityComparisonDictionary();
        return instance;
    }

    public void addComparisonModel(Class clazz, ComparisonModel model) {
        dictionary.put(clazz, model);
    }

    public ComparisonModel getComparisonModel(Class clazz) {
        if (!dictionary.containsKey(clazz)) {
            try {
                dictionary.put(clazz, inspector.getComparisonModel(clazz));
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }

        }
        return dictionary.get(clazz);
    }
}
