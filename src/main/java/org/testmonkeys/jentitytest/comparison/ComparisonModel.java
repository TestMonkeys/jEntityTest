package org.testmonkeys.jentitytest.comparison;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ComparisonModel {
    private final Map<PropertyDescriptor, Comparator> comparisonMap;

    public ComparisonModel() {
        this.comparisonMap = new HashMap<>();
    }

    public void setComparisonPoint(PropertyDescriptor propertyDescriptor, Comparator comparator) {
        this.comparisonMap.put(propertyDescriptor, comparator);
    }

    public Set<PropertyDescriptor> getComparableProperties() {
        return this.comparisonMap.keySet();
    }

    public Comparator getComparator(PropertyDescriptor propertyDescriptor) {
        return this.comparisonMap.get(propertyDescriptor);
    }
}
