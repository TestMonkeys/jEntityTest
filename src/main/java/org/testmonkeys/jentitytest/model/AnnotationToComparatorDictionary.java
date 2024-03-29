package org.testmonkeys.jentitytest.model;

import lombok.extern.slf4j.Slf4j;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.abortConditions.AbortOnExpectNullCondition;
import org.testmonkeys.jentitytest.comparison.abortConditions.AbstractAbortCondition;
import org.testmonkeys.jentitytest.comparison.strategies.*;
import org.testmonkeys.jentitytest.comparison.validations.AbstractValidation;
import org.testmonkeys.jentitytest.comparison.validations.NotNullValidator;
import org.testmonkeys.jentitytest.comparison.validations.RegexValidation;
import org.testmonkeys.jentitytest.exceptions.JEntityModelException;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.*;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Annotation to comparator mapping class. This class is used during Entity inspection, and based on
 * registered mappings will provide the comparators for annotations used in the Entity.
 * <p>
 * Contains default comparisons but can register new annotations to comparator mappings at runtime.
 */
@Slf4j
public final class AnnotationToComparatorDictionary {

    private static AnnotationToComparatorDictionary instance;
    private final Map<Class<?>, Class<? extends Comparator>> mapping;
    private final Map<Class<?>, Class<? extends AbstractAbortCondition>> preConditionalMapping;
    private final Map<Class<?>, Class<? extends AbstractValidation>> validationsMapping;
    private final ReflectionUtils reflectionUtils = new ReflectionUtils();

    private AnnotationToComparatorDictionary() {
        log.debug("Initializing Annotation to Comparator Dictionary"); //log
        mapping = new HashMap<>();
        preConditionalMapping = new HashMap<>();
        validationsMapping = new HashMap<>();
        setPreConditionalCheckForAnnotation(AbortOnExpectNullCondition.class, IgnoreComparisonIfExpectedNull.class);
        setComparatorForAnnotation(IgnoreComparator.class, IgnoreComparison.class);
        setComparatorForAnnotation(ChildEntityComparator.class, ChildEntityComparison.class);
        setComparatorForAnnotation(DateTimeComparator.class, DateTimeComparison.class);
        setComparatorForAnnotation(StringComparator.class, StringComparison.class);
        setComparatorForAnnotation(ChildEntityListComparator.class, ChildEntityListComparison.class);
        setComparatorForAnnotation(RegexComparator.class, RegexInExpected.class);
        setValidatorForAnnotation(NotNullValidator.class, ValidateNotNull.class);
        setValidatorForAnnotation(RegexValidation.class, ValidateRegex.class);
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
        log.trace("Checking if {} has a comparator assigned", annotation); //log
        return mapping.containsKey(annotation.annotationType());
    }

    /**
     * Checks if provided annotation is linked to a comparator
     *
     * @param candidate annotation to check
     * @return boolean result of verification
     */
    public boolean hasPreConditionalCheckAssigned(Annotation candidate) {
        log.trace("Checking if {} has a comparator assigned", candidate); //log
        return preConditionalMapping.containsKey(candidate.annotationType());
    }

    /**
     * Checks if provided annotation is linked to a validator
     *
     * @param candidate annotation to check
     * @return boolean result of verification
     */
    public boolean hasValidationCheckAssigned(Annotation candidate) {
        log.trace("Checking if {} has a comparator assigned", candidate); //log
        return validationsMapping.containsKey(candidate.annotationType());
    }

    /**
     * Register or overwrite a mapping between an annotation and a comparator
     *
     * @param comparator comparator to register
     * @param annotation annotation to register
     * @throws JEntityTestException in case the comparator or annotation is null
     */
    public void setComparatorForAnnotation(Class<? extends Comparator> comparator, Class<?> annotation) {
        log.debug("Registering Comparator {} for Annotation {}", comparator, annotation); //log
        if (comparator == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_comparator_null));
        if (annotation == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_annotation_null));
        mapping.put(annotation, comparator);
    }

    /**
     * Register or overwrite a mapping between an annotation and a comparator
     *
     * @param comparator comparator to register
     * @param annotation annotation to register
     * @throws JEntityTestException in case the comparator or annotation is null
     */
    public void setPreConditionalCheckForAnnotation(Class<? extends AbstractAbortCondition> comparator, Class<?> annotation) {
        log.debug("Registering Comparator {} for Annotation {}", comparator, annotation); //log
        if (comparator == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_comparator_null));
        if (annotation == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_annotation_null));
        preConditionalMapping.put(annotation, comparator);
    }

    /**
     * Register or overwrite a mapping between an annotation and a comparator
     *
     * @param comparator comparator to register
     * @param annotation annotation to register
     * @throws JEntityTestException in case the comparator or annotation is null
     */
    public void setValidatorForAnnotation(Class<? extends AbstractValidation> comparator, Class<?> annotation) {
        log.debug("Registering Comparator {} for Annotation {}", comparator, annotation); //log
        if (comparator == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_comparator_null));
        if (annotation == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_annotation_null));
        validationsMapping.put(annotation, comparator);
    }

    /**
     * Gets the comparator assigned to the provided annotation
     *
     * @param annotation annotation
     * @return Comparator for provided annotation
     * @throws JEntityTestException in case comparator could not be provided
     */
    public Comparator getComparatorForAnnotation(Annotation annotation) {
        log.trace("Getting comparator for Annotation {}", annotation); //log
        return getStrategyForAnnotation(annotation, mapping);
    }

    /**
     * Gets the comparator assigned to the provided annotation
     *
     * @param annotation annotation
     * @return Comparator for provided annotation
     * @throws JEntityTestException in case comparator could not be provided
     */
    public AbstractAbortCondition getPreComparisonCheckForAnnotation(Annotation annotation) {
        log.trace("Getting pre-comparison check for Annotation {}", annotation); //log
        return getStrategyForAnnotation(annotation, preConditionalMapping);
    }

    /**
     * Gets the comparator assigned to the provided annotation
     *
     * @param annotation annotation
     * @return Comparator for provided annotation
     * @throws JEntityTestException in case comparator could not be provided
     */
    public AbstractValidation getValidationForAnnotation(Annotation annotation) {
        log.trace("Getting validation check for Annotation {}", annotation); //log
        return getStrategyForAnnotation(annotation, validationsMapping);
    }

    private <T> T getStrategyForAnnotation(Annotation annotation, Map<Class<?>, Class<? extends T>> annotationToStrategyMapping) {
        if (annotation == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_annotation_null));

        if (annotationToStrategyMapping.containsKey(annotation.annotationType())) {
            return reflectionUtils.initializeStrategyByAnnotation(annotation, annotationToStrategyMapping.get(annotation.annotationType()));
        }

        throw new JEntityModelException(
                MessageFormat.format(Resources.getString(Resources.ERR_NO_COMPARATOR_DEFINED_FOR_ANNOTATION),
                        annotation.annotationType().getName()));
    }


}
