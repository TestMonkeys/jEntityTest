package org.testmonkeys.jentitytest.model.yaml;

import lombok.Data;

import java.util.Map;

/**
 * Yaml model for strategy definition
 * a strategy is defined by name and can have parameters
 */
@Data
public class StrategyDefinition {
    private String strategy;
    private Map<String, Object> parameters;
}
