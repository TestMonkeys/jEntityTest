package org.testmonkeys.jentitytest.inspect;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.entity.ChildEntityComparator;
import org.testmonkeys.jentitytest.comparison.property.DateTimeComparator;
import org.testmonkeys.jentitytest.comparison.property.IgnoreComparator;
import org.testmonkeys.jentitytest.framework.ChildEntityComparison;
import org.testmonkeys.jentitytest.framework.DateTimeComparison;
import org.testmonkeys.jentitytest.framework.IgnoreComparison;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ModelToComparisonMap {

    private static ModelToComparisonMap instance;
    private final Map<Class, Class> mapping;

    private ModelToComparisonMap() {
        mapping = new HashMap<>();
        mapping.put(IgnoreComparison.class, IgnoreComparator.class);
        mapping.put(ChildEntityComparison.class, ChildEntityComparator.class);
        mapping.put(DateTimeComparison.class, DateTimeComparator.class);
    }

    public static ModelToComparisonMap getInstance() {
        if (instance == null)
            instance = new ModelToComparisonMap();
        return instance;
    }


    public boolean mapsToComparator(Annotation annotation) {
        return mapping.containsKey(annotation.annotationType());
    }

    public void setComparatorForAnnotation(Class<? extends Comparator> comparator, Class<?> annotation) throws JEntityTestException {
        if (comparator == null)
            throw new JEntityTestException("Comparator can not be null");
        if (annotation == null)
            throw new JEntityTestException("Annotation can not be null");
        mapping.put(annotation, comparator);
    }

    public Comparator getComparatorForAnnotation(Annotation annotation) throws JEntityTestException {
        if (annotation == null)
            throw new JEntityTestException("Annotation can not be null");

        if (mapping.containsKey(annotation.annotationType())) {
            return initializeComparator(annotation, mapping.get(annotation.annotationType()));
        }
        throw new JEntityTestException("There is no comparator defined for annotation " + annotation.annotationType().getName());
    }


    private Comparator initializeComparator(Annotation annotation, Class<? extends Comparator> type) throws JEntityTestException {
        try {

            Method[] methods = annotation.annotationType().getDeclaredMethods();

            Comparator comparator = type.newInstance();
            for (Method method : methods) {
                Field field = type.getDeclaredField(method.getName());
                field.setAccessible(true);
                field.set(comparator, method.invoke(annotation));
                field.setAccessible(false);
            }

            return comparator;
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            throw new JEntityTestException("Could not create Comparator for annotation " + annotation.annotationType().getName(), e);
        }
    }

}
