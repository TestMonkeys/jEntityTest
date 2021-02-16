package org.testmonkeys.jentitytest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testmonkeys.jentitytest.comparison.validations.AbstractValidation;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiStrategyFieldMapping<T> {

    private static final Logger LOG = LoggerFactory.getLogger(MultiStrategyFieldMapping.class);
    private final Map<PropertyDescriptor, List<T>> comparisonMap;

    public MultiStrategyFieldMapping() {
        this.comparisonMap = new HashMap<>();
    }

    /**
     * Adds a strategy for the property
     * @param propertyDescriptor property to add to the mapping
     * @param validation strategy used for this property
     */
    public void addStrategy(PropertyDescriptor propertyDescriptor, T validation) {
        LOG.debug("Adding strategy for {} using {}", propertyDescriptor.getName(), validation); //LOG
        if (!comparisonMap.containsKey(propertyDescriptor))
            comparisonMap.put(propertyDescriptor,new ArrayList<>());
        comparisonMap.get(propertyDescriptor).add(validation);
    }

    /**
     * Gets the strategies defined for the provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return list of strategies
     */
    public List<T> getStrategies(PropertyDescriptor propertyDescriptor) {
        return comparisonMap.get(propertyDescriptor);
    }

    /**
     * Checks if there are any strategies registered for provided propertyDescriptor in the current model
     * @param propertyDescriptor
     * @return
     */
    public boolean hasStrategy(PropertyDescriptor propertyDescriptor) {
        return comparisonMap.containsKey(propertyDescriptor);
    }
}
