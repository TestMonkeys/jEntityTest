package org.testmonkeys.jentitytest.model.yaml;

import lombok.Data;

import java.util.Map;

/**
 * Yaml ComparisonModel definition root
 * contains:
 * - entity name (class name)
 * - properties comparison model
 */
@Data
public class EntityYamlComparisonDefinition {
    private String entity;
    private Map<String, FieldMappingDefinitions> properties;
}
