package org.testmonkeys.jentitytest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.strategies.*;
import org.testmonkeys.jentitytest.exceptions.*;
import org.testmonkeys.jentitytest.framework.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Annotation to comparator mapping class. This class is used during Entity inspection, and based on
 * registered mappings will provide the comparators for annotations used in the Entity.
 * <p>
 * Contains default comparisons but can register new annotations to comparator mappings at runtime.
 */

public final class AnnotationToComparatorDictionary {

    private static final Logger LOG = LoggerFactory.getLogger(AnnotationToComparatorDictionary.class);

    private static AnnotationToComparatorDictionary instance;
    private final Map<Class<?>, Class<? extends Comparator>> mapping;

    private AnnotationToComparatorDictionary() {
        LOG.debug("Initializing Annotation to Comparator Dictionary"); //LOG
        mapping = new HashMap<>();
        setComparatorForAnnotation(IgnoreComparator.class,IgnoreComparison.class);
        setComparatorForAnnotation(ChildEntityComparator.class,ChildEntityComparison.class);
        setComparatorForAnnotation(DateTimeComparator.class,DateTimeComparison.class);
        setComparatorForAnnotation(StringComparator.class,StringComparison.class);
        setComparatorForAnnotation(ChildEntityListComparator.class,ChildEntityListComparison.class);
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
        LOG.trace("Checking if {} has a comparator assigned",annotation); //LOG
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
        LOG.debug("Registering Comparator {} for Annotation {}",comparator,annotation); //LOG
        if (comparator == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_comparator_null));
        if (annotation == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_annotation_null));
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
        LOG.trace("Getting comparator for Annotation {}",annotation); //LOG
        if (annotation == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_annotation_null));

        if (mapping.containsKey(annotation.annotationType())) {
            return initializeComparator(annotation, mapping.get(annotation.annotationType()));
        }
        throw new JEntityModelException(
                MessageFormat.format(Resources.getString(Resources.ERR_NO_COMPARATOR_DEFINED_FOR_ANNOTATION),
                annotation.annotationType().getName()));
    }


    /**
     * @param annotation
     * @param type
     * @return
     * @throws JEntityTestException
     */
    private Comparator initializeComparator(Annotation annotation, Class<? extends Comparator> type) {
        LOG.trace("Starting initialization for comparator {}",type); //LOG
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor annotationConstructor = null;
        for (Constructor candidate : constructors) {
            if ((candidate.getParameterCount() == 1)
                    && (annotation.annotationType().isAssignableFrom(candidate.getParameterTypes()[0]))) {
                annotationConstructor = candidate;
                break;
            }
        }
        //noinspection OverlyBroadCatchBlock
        try {
            if (annotationConstructor != null) {
                LOG.trace("Initializing comparator {} using constructor with annotation parameter", type); //LOG
                return (Comparator) annotationConstructor.newInstance(annotation);
            } else {
                LOG.trace("Initializing comparator {} using default constructor", type); //LOG
                return type.getConstructor().newInstance();
            }
        } catch (InstantiationException e) {
            throw new ComparatorInstantiationException(type, annotation, e);
        } catch (IllegalAccessException e) {
            throw new ComparatorIllegalAccessException(type, annotation, e);
        } catch (Exception e) {
            throw new ComparatorInvocationTargetException(type, annotation, e);
        }
    }

}
