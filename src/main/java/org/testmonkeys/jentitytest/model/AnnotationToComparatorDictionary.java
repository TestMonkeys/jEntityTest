package org.testmonkeys.jentitytest.repo;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.entity.ChildEntityComparator;
import org.testmonkeys.jentitytest.comparison.entity.ChildEntityListComparator;
import org.testmonkeys.jentitytest.comparison.property.DateTimeComparator;
import org.testmonkeys.jentitytest.comparison.property.IgnoreComparator;
import org.testmonkeys.jentitytest.comparison.property.StringComparator;
import org.testmonkeys.jentitytest.framework.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Annotation to comparator mapping class. This class is used during Entity inspection, and based on
 * registered mappings will provide the comparators for annotations used in the Entity.
 *
 * Contains default comparisons but can register new annotations to comparator mappings at runtime.
 */
public final class AnnotationToComparatorDictionary {

    private static AnnotationToComparatorDictionary instance;
    private final Map<Class<?>, Class<? extends Comparator>> mapping;

    private AnnotationToComparatorDictionary() {
        mapping = new HashMap<>();
        mapping.put(IgnoreComparison.class, IgnoreComparator.class);
        mapping.put(ChildEntityComparison.class, ChildEntityComparator.class);
        mapping.put(DateTimeComparison.class, DateTimeComparator.class);
        mapping.put(StringComparison.class, StringComparator.class);
        mapping.put(ChildEntityListComparison.class, ChildEntityListComparator.class);
    }

    public static AnnotationToComparatorDictionary getInstance() {
        if (instance == null)
            instance = new AnnotationToComparatorDictionary();
        return instance;
    }

    /**
     * Checks if provided annotation is linked to a comparator
     * @param annotation annotation to check
     * @return boolean result of verification
     */
    public boolean hasComparatorAssinged(Annotation annotation) {
        return mapping.containsKey(annotation.annotationType());
    }

    /**
     * Register or overwrite a mapping between an annotation and a comparator
     * @param comparator comparator to register
     * @param annotation annotation to register
     * @throws JEntityTestException in case the comparator or annotation is null
     */
    public void setComparatorForAnnotation(Class<? extends Comparator> comparator, Class<?> annotation) {
        if (comparator == null)
            throw new JEntityTestException("Comparator can not be null");
        if (annotation == null)
            throw new JEntityTestException("Annotation can not be null");
        mapping.put(annotation, comparator);
    }

    /**
     * Gets the comparator assigned to the provided annotation
     *
     * @param annotation annotation
     * @return Comparator for provided annotation
     * @throws JEntityTestException in case comparator could not be provided
     */
    public Comparator getComparatorForAnnotation(Annotation annotation) {
        if (annotation == null)
            throw new JEntityTestException("Annotation can not be null");

        if (mapping.containsKey(annotation.annotationType())) {
            return initializeComparator(annotation, mapping.get(annotation.annotationType()));
        }
        throw new JEntityTestException("There is no comparator defined for annotation " + annotation.annotationType().getName());
    }


    /**
     * @param annotation
     * @param type
     * @return
     * @throws JEntityTestException
     */
    private Comparator initializeComparator(Annotation annotation, Class<? extends Comparator> type) {
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
