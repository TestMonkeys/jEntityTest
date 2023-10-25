package org.testmonkeys.jentitytest.model.yaml;

import lombok.extern.slf4j.Slf4j;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.strategies.SimpleTypeComparator;
import org.testmonkeys.jentitytest.exceptions.JEntityModelException;
import org.testmonkeys.jentitytest.model.ComparisonModel;
import org.testmonkeys.jentitytest.model.ReflectionUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testmonkeys.jentitytest.Resources.err_yaml_no_property_in_entity;

@Slf4j
public class YamlModelParser {

    private final ReflectionUtils reflectionUtils = new ReflectionUtils();

    /**
     * Interprets yaml model and creates a ComparisonModel based on that
     *
     * @param inputStream input stream of the model
     * @return Comparison Model
     */
    public ComparisonModel readModel(InputStream inputStream) {
        Yaml yaml = new Yaml();

        EntityYamlComparisonDefinition yamlModel = yaml.loadAs(inputStream, EntityYamlComparisonDefinition.class);


        Class<?> testEntityClass = reflectionUtils.getEntityTypeForName(yamlModel.getEntity());
        PropertyDescriptor[] testEntityPropertyDescriptors = reflectionUtils.getPropertyDescriptors(testEntityClass);

        validateAllDefinedPropertiesInYamlModel(testEntityPropertyDescriptors, yamlModel);

        ComparisonModel model = new ComparisonModel();
        model.setEntityType(testEntityClass);

        for (PropertyDescriptor propertyDescriptor : testEntityPropertyDescriptors) {
            createPropertyModel(propertyDescriptor, model, yamlModel);
        }
        return model;
    }

    private void validateAllDefinedPropertiesInYamlModel(PropertyDescriptor[] testEntityPropertyDescriptors, EntityYamlComparisonDefinition yamlModel) {
        Set<String> entityProperties = Arrays.stream(testEntityPropertyDescriptors).map(FeatureDescriptor::getName).collect(Collectors.toSet());
        if (!entityProperties.containsAll(yamlModel.getProperties().keySet())) {
            Set<String> yamlProperties = yamlModel.getProperties().keySet();
            yamlProperties.removeAll(entityProperties);
            log.error("Found entity does not contain properties: {}", yamlProperties);
            throw new JEntityModelException(MessageFormat.format(Resources.getString(err_yaml_no_property_in_entity), yamlModel.getEntity(), yamlProperties));
        }
    }

    private void createPropertyModel(PropertyDescriptor propertyDescriptor, ComparisonModel model, EntityYamlComparisonDefinition obj) {
        if (obj.getProperties().containsKey(propertyDescriptor.getDisplayName())) {
            createPropertyComparisonModelFromYamlConfiguration(propertyDescriptor, model, obj);
        } else {
            createDefaultPropertyModel(propertyDescriptor, model);
        }
    }

    private void createPropertyComparisonModelFromYamlConfiguration(PropertyDescriptor propertyDescriptor, ComparisonModel model, EntityYamlComparisonDefinition obj) {
        FieldMappingDefinitions fmDefinition = obj.getProperties().get(propertyDescriptor.getDisplayName());

        initModelValidators(propertyDescriptor, model, fmDefinition.getValidators());
        initModelAbortConditions(propertyDescriptor, model, fmDefinition.getAbortConditions());

        if (fmDefinition.getComparisonStrategy() != null) {
            model.setComparisonPoint(propertyDescriptor, new PropertyComparisonWrapper(reflectionUtils.initializeStrategy(fmDefinition.getComparisonStrategy())));
        }
    }

    private void initModelValidators(PropertyDescriptor propertyDescriptor, ComparisonModel model, List<StrategyDefinition> strategies) {
        if (strategies != null) {
            for (StrategyDefinition strategy : strategies) {
                model.addValidation(propertyDescriptor, reflectionUtils.initializeStrategy(strategy));
            }
        }
    }

    private void initModelAbortConditions(PropertyDescriptor propertyDescriptor, ComparisonModel model, List<StrategyDefinition> strategies) {
        if (strategies != null) {
            for (StrategyDefinition strategy : strategies) {
                model.addAbortCondition(propertyDescriptor, reflectionUtils.initializeStrategy(strategy));
            }
        }
    }


    private void createDefaultPropertyModel(PropertyDescriptor propertyDescriptor, ComparisonModel model) {
        model.setComparisonPoint(propertyDescriptor, new PropertyComparisonWrapper(new SimpleTypeComparator()));
    }


}
