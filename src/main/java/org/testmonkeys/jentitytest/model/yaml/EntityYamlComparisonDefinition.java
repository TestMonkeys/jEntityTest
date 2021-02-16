package org.testmonkeys.jentitytest.model.yaml;

import java.util.Map;

public class EntityYamlComparisonDefinition {
    private String entity;
    private Map<String, FieldMappingDefinitions> properties;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Map<String, FieldMappingDefinitions> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, FieldMappingDefinitions> properties) {
        this.properties = properties;
    }
}
