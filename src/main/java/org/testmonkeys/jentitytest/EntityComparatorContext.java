package org.testmonkeys.jentitytest;

import lombok.Getter;
import org.testmonkeys.jentitytest.comparison.abortConditions.AbortOnExpectNullCondition;
import org.testmonkeys.jentitytest.comparison.strategies.ChildEntityComparator;
import org.testmonkeys.jentitytest.comparison.strategies.ChildEntityListComparator;
import org.testmonkeys.jentitytest.comparison.strategies.DateTimeComparator;
import org.testmonkeys.jentitytest.comparison.strategies.StringComparator;
import org.testmonkeys.jentitytest.comparison.validations.NotNullValidator;
import org.testmonkeys.jentitytest.comparison.validations.RegexValidation;
import org.testmonkeys.jentitytest.model.ComparisonModel;
import org.testmonkeys.jentitytest.model.EntityToComparisonModelDictionary;
import org.testmonkeys.jentitytest.model.yaml.YamlModelParser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class EntityComparatorContext {

    private static EntityComparatorContext instance;
    @Getter
    private final Map<String, Class<?>> strategyShortNames;
    private final YamlModelParser yamlModelParser = new YamlModelParser();

    private EntityComparatorContext() {
        strategyShortNames = new HashMap<>();
        strategyShortNames.put("RegexValidation", RegexValidation.class);
        strategyShortNames.put("NotNullValidator", NotNullValidator.class);
        strategyShortNames.put("StringComparison", StringComparator.class);
        strategyShortNames.put("DateTimeComparison", DateTimeComparator.class);
        strategyShortNames.put("ChildEntityComparison", ChildEntityComparator.class);
        strategyShortNames.put("ChildEntityListComparison", ChildEntityListComparator.class);
        strategyShortNames.put("AbortIfExpectedNull", AbortOnExpectNullCondition.class);
    }

    public synchronized static EntityComparatorContext getInstance() {
        if (instance == null) {
            instance = new EntityComparatorContext();
        }
        return instance;
    }

    public void defineComparisonModel(InputStream is) {
        ComparisonModel model = yamlModelParser.readModel(is);
        EntityToComparisonModelDictionary.getInstance().addComparisonModel(model.getEntityType(), model);
    }

}
