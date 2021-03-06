package org.testmonkeys.jentitytest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.abortConditions.AbstractAbortCondition;
import org.testmonkeys.jentitytest.comparison.validations.AbstractValidation;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * Describes comparison model for an strategies.
 * Can provide the list of comparable properties and comparator for those properties.
 */
public class ComparisonModel {

    private static final Logger LOG = LoggerFactory.getLogger(ComparisonModel.class);
    private final Map<PropertyDescriptor, List<AbstractValidation>> validations;
    private final Map<PropertyDescriptor, List<AbstractAbortCondition>> abortConditions;
    private final Map<PropertyDescriptor, PropertyComparisonWrapper> comparisonMap;


    public ComparisonModel() {
        LOG.debug("Creating new comparison model"); //LOG
        validations = new HashMap<>();
        abortConditions = new HashMap<>();
        comparisonMap = new HashMap<>();
    }

    /**
     * Adds a validation point for the property
     * @param propertyDescriptor property to add to the comparison
     * @param validation validation used for this property
     */
    public void setValidation(PropertyDescriptor propertyDescriptor, AbstractValidation validation) {
        LOG.debug("Adding validation for {} using {}", propertyDescriptor.getName(), validation); //LOG
        if (!validations.containsKey(propertyDescriptor))
            validations.put(propertyDescriptor,new ArrayList<>());
        validations.get(propertyDescriptor).add(validation);
    }

    /**
     * Gets the validation checks for the provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return list of validation checks
     */
    public List<AbstractValidation> getValidations(PropertyDescriptor propertyDescriptor) {
        return validations.get(propertyDescriptor);
    }

    /**
     * Checks if there are any validation checks registered for provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public boolean hasValidations(PropertyDescriptor propertyDescriptor) {
        return validations.containsKey(propertyDescriptor);
    }

    /**
     * Adds comparison point for strategies
     * @param propertyDescriptor property to add to the comparison
     * @param comparator comparator used for this property
     */
    public void setComparisonPoint(PropertyDescriptor propertyDescriptor, PropertyComparisonWrapper comparator) {
        LOG.debug("Adding comparison for {} using {}", propertyDescriptor.getName(), comparator.getComparator()); //LOG
        comparisonMap.put(propertyDescriptor, comparator);
    }

    /**
     * Adds abort condition check
     * This will stop comparison with the expected in case if the abortCondition fails, but will not mark the field
     * as a comparison failure.
     * @param propertyDescriptor property to add to the comparison
     * @param abortCondition abort condition used for this property
     */
    public void setAbortCondition(PropertyDescriptor propertyDescriptor, AbstractAbortCondition abortCondition) {
        LOG.debug("Adding pre-conditional check for {} using {}", propertyDescriptor.getName(), abortCondition); //LOG
        if (!abortConditions.containsKey(propertyDescriptor))
            abortConditions.put(propertyDescriptor,new ArrayList<>());
        abortConditions.get(propertyDescriptor).add(abortCondition);
    }

    /**
     * Gets the set of properties that this model contains
     * @return
     */
    public Set<PropertyDescriptor> getComparableProperties() {
        return comparisonMap.keySet();
    }

    /**
     * Gets the comparator for the provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public PropertyComparisonWrapper getComparator(PropertyDescriptor propertyDescriptor) {
        return comparisonMap.get(propertyDescriptor);
    }

    /**
     * Gets the pre-conditional checks for the provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public List<AbstractAbortCondition> getAbortConditionChecks(PropertyDescriptor propertyDescriptor) {
        return abortConditions.get(propertyDescriptor);
    }

    /**
     * Checks if there are any pre-conditional checks registered for provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public boolean hasAbortConditions(PropertyDescriptor propertyDescriptor) {
        return abortConditions.containsKey(propertyDescriptor);
    }
}
