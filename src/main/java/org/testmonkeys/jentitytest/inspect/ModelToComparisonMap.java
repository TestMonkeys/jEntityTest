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
    private final Map<Class<?>, Class<? extends Comparator>> mapping;

    private ModelToComparisonMap() {
        this.mapping = new HashMap<>();
        this.mapping.put(IgnoreComparison.class, IgnoreComparator.class);
        this.mapping.put(ChildEntityComparison.class, ChildEntityComparator.class);
        this.mapping.put(DateTimeComparison.class, DateTimeComparator.class);
    }

    public static ModelToComparisonMap getInstance() {
        if (ModelToComparisonMap.instance == null)
            ModelToComparisonMap.instance = new ModelToComparisonMap();
        return ModelToComparisonMap.instance;
    }


    public boolean mapsToComparator(Annotation annotation) {
        return this.mapping.containsKey(annotation.annotationType());
    }

    public void setComparatorForAnnotation(Class<? extends Comparator> comparator, Class<?> annotation) throws JEntityTestException {
        if (comparator == null)
            throw new JEntityTestException("Comparator can not be null");
        if (annotation == null)
            throw new JEntityTestException("Annotation can not be null");
        this.mapping.put(annotation, comparator);
    }

    public Comparator getComparatorForAnnotation(Annotation annotation) throws JEntityTestException {
        if (annotation == null)
            throw new JEntityTestException("Annotation can not be null");

        if (this.mapping.containsKey(annotation.annotationType())) {
            return this.initializeComparator(annotation, this.mapping.get(annotation.annotationType()));
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
