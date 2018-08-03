package org.testmonkeys.jentitytest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Describes comparison model for an strategies.
 * Can provide the list of comparable properties and comparator for those properties.
 */
public class ComparisonModel {

    private static final Logger LOG = LoggerFactory.getLogger(ComparisonModel.class);
    private final Map<PropertyDescriptor, PropertyComparisonWrapper> comparisonMap;

    public ComparisonModel() {
        LOG.debug("Creating new comparison model"); //LOG
        comparisonMap = new HashMap<>();
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
}
