package org.testmonkeys.jentitytest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * Describes comparison model for an strategies.
 * Can provide the list of comparable properties and comparator for those properties.
 */
public class ComparisonModel {

    private static final Logger LOG = LoggerFactory.getLogger(ComparisonModel.class);
    private final Map<PropertyDescriptor, PropertyComparisonWrapper> comparisonMap;
    private final Map<PropertyDescriptor, List<PropertyComparisonWrapper>> preConditionalChecks;

    public ComparisonModel() {
        LOG.debug("Creating new comparison model"); //LOG
        comparisonMap = new HashMap<>();
        preConditionalChecks = new HashMap<>();
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
     * Adds pre-conditional check
     * @param propertyDescriptor property to add to the comparison
     * @param comparator comparator used for this property
     */
    public void setPreConditionalCheck(PropertyDescriptor propertyDescriptor, PropertyComparisonWrapper comparator) {
        LOG.debug("Adding pre-conditional check for {} using {}", propertyDescriptor.getName(), comparator.getComparator()); //LOG
        if (!preConditionalChecks.containsKey(propertyDescriptor))
            preConditionalChecks.put(propertyDescriptor,new ArrayList<>());
        preConditionalChecks.get(propertyDescriptor).add(comparator);
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
    public List<PropertyComparisonWrapper> getPreConditionalChecks(PropertyDescriptor propertyDescriptor) {
        return preConditionalChecks.get(propertyDescriptor);
    }

    /**
     * Checks if there are any pre-conditional checks registered for provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public boolean hasPreConditionalCheck(PropertyDescriptor propertyDescriptor) {
        return preConditionalChecks.containsKey(propertyDescriptor);
    }
}
