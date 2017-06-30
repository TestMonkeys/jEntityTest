package org.testmonkeys.jentitytest.model;

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
    private final Map<PropertyDescriptor, PropertyComparisonWrapper> comparisonMap;

    public ComparisonModel() {
        this.comparisonMap = new HashMap<>();
    }

    /**
     * Adds comparison point for strategies
     * @param propertyDescriptor property to add to the comparison
     * @param comparator comparator used for this property
     */
    public void setComparisonPoint(PropertyDescriptor propertyDescriptor, PropertyComparisonWrapper comparator) {
        this.comparisonMap.put(propertyDescriptor, comparator);
    }

    public boolean hasComparisonPoint(PropertyDescriptor propertyDescriptor){
        return comparisonMap.containsKey(propertyDescriptor);
    }

    /**
     * Gets the set of properties that this model contains
     * @return
     */
    public Set<PropertyDescriptor> getComparableProperties() {
        return this.comparisonMap.keySet();
    }

    /**
     * Gets the comparator for the provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public PropertyComparisonWrapper getComparator(PropertyDescriptor propertyDescriptor) {
        return this.comparisonMap.get(propertyDescriptor);
    }
}
