package org.testmonkeys.jentitytest.model;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.strategies.*;
import org.testmonkeys.jentitytest.exceptions.ComparatorIllegalAccessException;
import org.testmonkeys.jentitytest.exceptions.ComparatorInstantiationException;
import org.testmonkeys.jentitytest.exceptions.ComparatorInvocationTargetException;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Annotation to comparator mapping class. This class is used during Entity inspection, and based on
 * registered mappings will provide the comparators for annotations used in the Entity.
 * <p>
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

    public static synchronized AnnotationToComparatorDictionary getInstance() {
        if (instance == null)
            instance = new AnnotationToComparatorDictionary();
        return instance;
    }

    /**
     * Checks if provided annotation is linked to a comparator
     *
     * @param annotation annotation to check
     * @return boolean result of verification
     */
    public boolean hasComparatorAssigned(Annotation annotation) {
        return mapping.containsKey(annotation.annotationType());
    }

    /**
     * Register or overwrite a mapping between an annotation and a comparator
     *
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
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor annotationConstructor = null;
        for (Constructor candidate : constructors) {
            if ((candidate.getParameterCount() == 1) && (annotation.annotationType().isAssignableFrom(candidate.getParameterTypes()[0]))) {
                annotationConstructor = candidate;
                break;
            }
        }
        try {
            if (annotationConstructor != null)
                return (Comparator) annotationConstructor.newInstance(annotation);
            else
                return type.getConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new ComparatorInstantiationException(type, annotation, e);
        } catch (IllegalAccessException e) {
            throw new ComparatorIllegalAccessException(type, annotation, e);
        } catch (Exception e) {
            throw new ComparatorInvocationTargetException(type, annotation, e);
        }
    }

}
