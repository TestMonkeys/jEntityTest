package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.matchers.ObjectPropertyComparator;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ComparisonModel {
    private final Map<PropertyDescriptor, ObjectPropertyComparator> comparisonMap;

    public ComparisonModel() {
        comparisonMap = new HashMap<>();
    }

    public void setComparisonPoint(PropertyDescriptor propertyDescriptor, ObjectPropertyComparator comparator) {
        comparisonMap.put(propertyDescriptor, comparator);
    }

    public Set<PropertyDescriptor> getComparableProperties() {
        return comparisonMap.keySet();
    }

    public ObjectPropertyComparator getComparator(PropertyDescriptor propertyDescriptor) {
        return comparisonMap.get(propertyDescriptor);
    }
}
