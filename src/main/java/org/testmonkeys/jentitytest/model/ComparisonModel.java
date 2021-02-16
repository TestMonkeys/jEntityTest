package org.testmonkeys.jentitytest.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ComparisonModel {

    private static final Logger LOG = LoggerFactory.getLogger(ComparisonModel.class);
    @Getter
    @Setter
    private Class<?> entityType;
    private final MultiStrategyFieldMapping<AbstractValidation> validations;
    private final MultiStrategyFieldMapping<AbstractAbortCondition> abortConditions;
    private final SingleStrategyFieldMapping<PropertyComparisonWrapper> comparisonMap;


    public ComparisonModel() {
        LOG.debug("Creating new comparison model"); //LOG
        validations = new MultiStrategyFieldMapping<>();
        abortConditions = new MultiStrategyFieldMapping<>();
        comparisonMap = new SingleStrategyFieldMapping<>();
    }

    /**
     * Adds a validation point for the property
     * @param propertyDescriptor property to add to the comparison
     * @param validation validation used for this property
     */
    public void addValidation(PropertyDescriptor propertyDescriptor, AbstractValidation validation) {
        LOG.debug("Adding validation for {} using {}", propertyDescriptor.getName(), validation); //LOG
        validations.addStrategy(propertyDescriptor,validation);
    }

    /**
     * Gets the validation checks for the provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return list of validation checks
     */
    public List<AbstractValidation> getValidations(PropertyDescriptor propertyDescriptor) {
        return validations.getStrategies(propertyDescriptor);
    }

    /**
     * Checks if there are any validation checks registered for provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public boolean hasValidations(PropertyDescriptor propertyDescriptor) {
        return validations.hasStrategy(propertyDescriptor);
    }

    /**
     * Adds abort condition check
     * This will stop comparison with the expected in case if the abortCondition fails, but will not mark the field
     * as a comparison failure.
     * @param propertyDescriptor property to add to the comparison
     * @param abortCondition abort condition used for this property
     */
    public void addAbortCondition(PropertyDescriptor propertyDescriptor, AbstractAbortCondition abortCondition) {
        LOG.debug("Adding pre-conditional check for {} using {}", propertyDescriptor.getName(), abortCondition); //LOG
        abortConditions.addStrategy(propertyDescriptor,abortCondition);
    }

    /**
     * Checks if there are any pre-conditional checks registered for provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public boolean hasAbortConditions(PropertyDescriptor propertyDescriptor) {
        return abortConditions.hasStrategy(propertyDescriptor);
    }

    /**
     * Gets the pre-conditional checks for the provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public List<AbstractAbortCondition> getAbortConditionChecks(PropertyDescriptor propertyDescriptor) {
        return abortConditions.getStrategies(propertyDescriptor);
    }



    /**
     * Adds comparison point for strategies
     * @param propertyDescriptor property to add to the comparison
     * @param comparator comparator used for this property
     */
    public void setComparisonPoint(PropertyDescriptor propertyDescriptor, PropertyComparisonWrapper comparator) {
        LOG.debug("Adding comparison for {} using {}", propertyDescriptor.getName(), comparator.getComparator()); //LOG
        comparisonMap.setStrategy(propertyDescriptor, comparator);
    }

    /**
     * Gets the set of properties that this model contains
     * @return
     */
    public Set<PropertyDescriptor> getComparableProperties() {
        return comparisonMap.getMappedProperties();
    }

    /**
     * Gets the comparator for the provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public PropertyComparisonWrapper getComparator(PropertyDescriptor propertyDescriptor) {
        return comparisonMap.getStrategy(propertyDescriptor);
    }


}
