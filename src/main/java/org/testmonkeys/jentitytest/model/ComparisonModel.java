package org.testmonkeys.jentitytest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.abortConditions.AbstractAbortCondition;
import org.testmonkeys.jentitytest.comparison.validations.AbstractValidation;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Set;

/**
 * ComparisonModel describes an entity by defining the mappings between PropertyDescriptor and Validation,
 * AbortConditions and Comparison strategy instances.
 */
@Slf4j
public class ComparisonModel {

    private final MultiStrategyFieldMapping<AbstractValidation> validations;
    private final MultiStrategyFieldMapping<AbstractAbortCondition> abortConditions;
    private final SingleStrategyFieldMapping<PropertyComparisonWrapper> comparisonMap;
    @Getter
    @Setter
    private Class<?> entityType;


    public ComparisonModel() {
        log.debug("Creating new comparison model"); //log
        validations = new MultiStrategyFieldMapping<>();
        abortConditions = new MultiStrategyFieldMapping<>();
        comparisonMap = new SingleStrategyFieldMapping<>();
    }

    /**
     * Adds a validation point for the property
     *
     * @param propertyDescriptor property to add to the comparison
     * @param validation         validation used for this property
     */
    public void addValidation(PropertyDescriptor propertyDescriptor, AbstractValidation validation) {
        log.debug("Adding validation for {} using {}", propertyDescriptor.getName(), validation); //log
        validations.addStrategy(propertyDescriptor, validation);
    }

    /**
     * Gets the validation checks for the provided propertyDescriptor in the current model
     *
     * @param propertyDescriptor property
     * @return list of validation checks
     */
    public List<AbstractValidation> getValidations(PropertyDescriptor propertyDescriptor) {
        return validations.getStrategies(propertyDescriptor);
    }

    /**
     * Checks if there are any validation checks registered for provided propertyDescriptor in the current model
     *
     * @param propertyDescriptor property
     * @return true if has any validations defined
     */
    public boolean hasValidations(PropertyDescriptor propertyDescriptor) {
        return validations.hasStrategy(propertyDescriptor);
    }

    /**
     * Adds abort condition check
     * This will stop comparison with the expected in case if the abortCondition fails, but will not mark the field
     * as a comparison failure.
     *
     * @param propertyDescriptor property to add to the comparison
     * @param abortCondition     abort condition used for this property
     */
    public void addAbortCondition(PropertyDescriptor propertyDescriptor, AbstractAbortCondition abortCondition) {
        log.debug("Adding pre-conditional check for {} using {}", propertyDescriptor.getName(), abortCondition); //log
        abortConditions.addStrategy(propertyDescriptor, abortCondition);
    }

    /**
     * Checks if there are any pre-conditional checks registered for provided propertyDescriptor in the current model
     *
     * @param propertyDescriptor property
     * @return true if has any abort conditions defined
     */
    public boolean hasAbortConditions(PropertyDescriptor propertyDescriptor) {
        return abortConditions.hasStrategy(propertyDescriptor);
    }

    /**
     * Gets the pre-conditional checks for the provided propertyDescriptor in the current model
     *
     * @param propertyDescriptor property
     * @return abort conditions list
     */
    public List<AbstractAbortCondition> getAbortConditionChecks(PropertyDescriptor propertyDescriptor) {
        return abortConditions.getStrategies(propertyDescriptor);
    }


    /**
     * Adds comparison point for strategies
     *
     * @param propertyDescriptor property to add to the comparison
     * @param comparator         comparator used for this property
     */
    public void setComparisonPoint(PropertyDescriptor propertyDescriptor, PropertyComparisonWrapper comparator) {
        log.debug("Adding comparison for {} using {}", propertyDescriptor.getName(), comparator.getComparator()); //log
        comparisonMap.setStrategy(propertyDescriptor, comparator);
    }

    /**
     * Gets the set of properties that this model contains
     *
     * @return set of comparable properties
     */
    public Set<PropertyDescriptor> getComparableProperties() {
        return comparisonMap.getMappedProperties();
    }

    /**
     * Gets the comparator for the provided propertyDescriptor in the current model
     *
     * @param propertyDescriptor property
     * @return comparions wrapper instance
     */
    public PropertyComparisonWrapper getComparator(PropertyDescriptor propertyDescriptor) {
        return comparisonMap.getStrategy(propertyDescriptor);
    }


}
