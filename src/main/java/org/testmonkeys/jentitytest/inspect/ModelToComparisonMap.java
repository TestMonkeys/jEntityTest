package org.testmonkeys.jentitytest.inspect;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.entity.ChildEntityComparator;
import org.testmonkeys.jentitytest.comparison.entity.EntityComparator;
import org.testmonkeys.jentitytest.comparison.property.IgnoreComparator;
import org.testmonkeys.jentitytest.comparison.property.SimpleTypeComparator;
import org.testmonkeys.jentitytest.framework.ChildEntityComparison;
import org.testmonkeys.jentitytest.framework.IgnoreComparison;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ModelToComparisonMap {

    private static ModelToComparisonMap instance;
    private final Map<Class, Class> mapping;

    private ModelToComparisonMap() {
        mapping = new HashMap<>();
        mapping.put(IgnoreComparison.class, IgnoreComparator.class);
        mapping.put(ChildEntityComparison.class, ChildEntityComparator.class);
    }

    public static ModelToComparisonMap getInstance() {
        if (instance == null)
            instance = new ModelToComparisonMap();
        return instance;
    }

    public void setComparatorForAnnotation(Class<? extends Comparator> comparator, Class<?> annotation) {
        mapping.put(annotation, comparator);
    }

    public Comparator getComparatorForAnnotation(Annotation annotation) {

        if (mapping.containsKey(annotation.annotationType()))
            try {
                return (Comparator) mapping.get(annotation.annotationType()).getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        return new SimpleTypeComparator();
    }
}
