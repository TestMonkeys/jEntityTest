package org.testmonkeys.jentitytest.model.yaml;

import lombok.Data;

import java.util.List;

/**
 * Yaml model class for field definitions
 * one field can have:
 * - multiple validations, like NotNull, RegexValidation
 * - multiple abort conditions like abort if expected is null or other custom abort conditions
 * - a single comparison strategy to be used against the expected value
 */
@Data
public class FieldMappingDefinitions {

    private List<StrategyDefinition> validators;
    private List<StrategyDefinition> abortConditions;
    private StrategyDefinition comparisonStrategy;

}
