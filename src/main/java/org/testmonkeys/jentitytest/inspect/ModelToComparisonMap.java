package org.testmonkeys.jentitytest.inspect;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.entity.ChildEntityComparator;
import org.testmonkeys.jentitytest.comparison.property.IgnoreComparator;
import org.testmonkeys.jentitytest.comparison.property.SimpleTypeComparator;
import org.testmonkeys.jentitytest.framework.ChildEntityComparison;
import org.testmonkeys.jentitytest.framework.IgnoreComparison;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

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


    public boolean mapsToComparator(Annotation annotation){
        return mapping.containsKey(annotation.annotationType());
    }

    public void setComparatorForAnnotation(Class<? extends Comparator> comparator, Class<?> annotation) throws JEntityTestException{
        if (comparator==null)
            throw new JEntityTestException("Comparator can not be null");
        if (annotation==null)
            throw new JEntityTestException("Annotation can not be null");
        mapping.put(annotation, comparator);
    }

    public Comparator getComparatorForAnnotation(Annotation annotation) throws JEntityTestException {
        if (annotation == null)
            throw new JEntityTestException("Annotation can not be null");

        if (mapping.containsKey(annotation.annotationType())) {
            try {
                return (Comparator) mapping.get(annotation.annotationType()).getConstructor().newInstance();
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                throw new JEntityTestException("Could not create Comparator for annotation " + annotation.annotationType().getName(), e);
            }
        }
        throw new JEntityTestException("There is no comparator defined for annotation " + annotation.annotationType().getName());
    }
}
