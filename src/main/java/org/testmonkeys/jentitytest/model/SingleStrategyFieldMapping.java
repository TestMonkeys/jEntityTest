package org.testmonkeys.jentitytest.model;

import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class SingleStrategyFieldMapping<T> {
    private final Map<PropertyDescriptor, T> strategyMap;

    public SingleStrategyFieldMapping() {
        this.strategyMap = new HashMap<>();
    }

    /**
     * Sets a strategy for a property
     *
     * @param propertyDescriptor property mapped
     * @param strategy           strategy mapped
     */
    public void setStrategy(PropertyDescriptor propertyDescriptor, T strategy) {
        log.debug("Adding strategy for {} using {}", propertyDescriptor.getName(), strategy); //log
        strategyMap.put(propertyDescriptor, strategy);
    }

    /**
     * Gets the strategy for the provided propertyDescriptor in the current model mapping
     *
     * @param propertyDescriptor property descriptor for which to retrieve the strategy
     * @return strategy
     */
    public T getStrategy(PropertyDescriptor propertyDescriptor) {
        return strategyMap.get(propertyDescriptor);
    }

    /**
     * Gets the set of properties for which a mapping was set
     *
     * @return property descriptor set
     */
    public Set<PropertyDescriptor> getMappedProperties() {
        return strategyMap.keySet();
    }
}
