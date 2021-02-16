package org.testmonkeys.jentitytest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SingleStrategyFieldMapping<T> {
    private static final Logger LOG = LoggerFactory.getLogger(SingleStrategyFieldMapping.class);
    private final Map<PropertyDescriptor,T> strategyMap;

    public SingleStrategyFieldMapping() {
        this.strategyMap = new HashMap<>();
    }

    /**
     * Sets a strategy for a property
     * @param propertyDescriptor property mapped
     * @param strategy strategy mapped
     */
    public void setStrategy(PropertyDescriptor propertyDescriptor, T strategy) {
        LOG.debug("Adding strategy for {} using {}", propertyDescriptor.getName(), strategy); //LOG
        strategyMap.put(propertyDescriptor, strategy);
    }

    /**
     * Gets the strategy for the provided propertyDescriptor in the current model mapping
     * @param propertyDescriptor
     * @return strategy
     */
    public T getStrategy(PropertyDescriptor propertyDescriptor) {
        return strategyMap.get(propertyDescriptor);
    }

    /**
     * Gets the set of properties for which a mapping was set
     * @return
     */
    public Set<PropertyDescriptor> getMappedProperties() {
        return strategyMap.keySet();
    }
}
