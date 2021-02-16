package org.testmonkeys.jentitytest.test.integration.yaml.parsing;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.EntityComparatorContext;
import org.testmonkeys.jentitytest.exceptions.JEntityModelException;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.InputStream;


public class YamlParsing {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void invalidYaml() {
        expectedException.expect(YAMLException.class);
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yamlComparisonModels/parsingErrorYamlModels/modelYamlIncorrect.yaml");
        EntityComparatorContext.getInstance().defineComparisonModel(inputStream);
    }

    @Test
    public void nonExisting_validationStrategy() {
        expectedException.expect(JEntityModelException.class);
        expectedException.expectMessage("No class found for described strategy:RegexValidationThatWasNotImplementedYet");
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yamlComparisonModels/parsingErrorYamlModels/modelValidationNotExisting.yaml");
        EntityComparatorContext.getInstance().defineComparisonModel(inputStream);
    }

    @Test
    public void nonExisting_abortConditionStrategy() {
        expectedException.expect(JEntityModelException.class);
        expectedException.expectMessage("No class found for described strategy:AbortConditionThatWasNotImplementedYet");
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yamlComparisonModels/parsingErrorYamlModels/modelAbortConditionNotExisting.yaml");
        EntityComparatorContext.getInstance().defineComparisonModel(inputStream);
    }

    @Test
    public void nonExisting_comparisonStrategy() {
        expectedException.expect(JEntityModelException.class);
        expectedException.expectMessage("No class found for described strategy:ComparatorThatWasNotImplementedYet");
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yamlComparisonModels/parsingErrorYamlModels/modelComparatorNotExisting.yaml");
        EntityComparatorContext.getInstance().defineComparisonModel(inputStream);
    }

    @Test
    public void strategy_extraParameter() {
        expectedException.expect(JEntityModelException.class);
        expectedException.expectMessage("org.testmonkeys.jentitytest.comparison.validations.RegexValidation strategy does not have parameters named: [failOnNullExtra]");
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yamlComparisonModels/parsingErrorYamlModels/modelWithExtraStrategyParameters.yaml");
        EntityComparatorContext.getInstance().defineComparisonModel(inputStream);
    }

    @Test
    public void strategy_invalidParameter() {
        expectedException.expect(JEntityModelException.class);
        expectedException.expectMessage("org.testmonkeys.jentitytest.comparison.validations.RegexValidation:failOnNull parameter assignment was impossible with value: trueIsNotABoolean (type mismatch)");
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yamlComparisonModels/parsingErrorYamlModels/modelWithInvalidStrategyParameters.yaml");
        EntityComparatorContext.getInstance().defineComparisonModel(inputStream);
    }

    @Test
    public void entity_notExisting() {
        expectedException.expect(JEntityModelException.class);
        expectedException.expectMessage("No class found for described entity:org.testmonkeys.jentitytest.nonExisting.Model");
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yamlComparisonModels/parsingErrorYamlModels/modelEntityNotExisting.yaml");
        EntityComparatorContext.getInstance().defineComparisonModel(inputStream);
    }

    @Test
    public void entity_property_notExisting() {
        expectedException.expect(JEntityModelException.class);
        expectedException.expectMessage("org.testmonkeys.jentitytest.test.integration.yaml.parsing.YamlParsingEntity entity does not contain a property named: [firstname]");
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yamlComparisonModels/parsingErrorYamlModels/modelEntityPropertyNotExisting.yaml");
        EntityComparatorContext.getInstance().defineComparisonModel(inputStream);
    }

}
