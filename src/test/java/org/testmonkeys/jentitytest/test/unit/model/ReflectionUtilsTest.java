package org.testmonkeys.jentitytest.test.unit.model;

import org.hamcrest.junit.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testmonkeys.jentitytest.exceptions.StrategyInstantiationByAnnotationException;
import org.testmonkeys.jentitytest.exceptions.StrategyInstantiationException;
import org.testmonkeys.jentitytest.model.ReflectionUtils;
import org.testmonkeys.jentitytest.model.yaml.StrategyDefinition;
import org.testmonkeys.jentitytest.test.unit.model.util.BadCheck;
import org.testmonkeys.jentitytest.test.unit.model.util.BadComparisonCustom;

import java.beans.IntrospectionException;
import java.lang.annotation.Annotation;

/**
 * Created by cpascal on 6/21/2017.
 */
public class ReflectionUtilsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private ReflectionUtils reflectionUtils;

    /**
     * initialization of EntityInspector,
     * generation of comparison model for ModelMultiAnnotated.class,
     * retrieval of comparable properties
     *
     * @throws IntrospectionException
     */
    @Before
    public void background() throws IntrospectionException {
        reflectionUtils = new ReflectionUtils();
        //comparisonMap.setComparatorForAnnotation(BadComparator.class, BadComparisonCustom.class);
    }

    /**
     * Check comparisonMap will throw exception if it has no comparator for annotation
     */
    @Test
    public void reflectionUtils_intialiseCheck_bad() throws Throwable {
        expectedException.expect(StrategyInstantiationByAnnotationException.class);
        expectedException.expectMessage("Could not create instance of Strategy: class org.testmonkeys.jentitytest.test.unit.model.util.BadCheck for annotation org.testmonkeys.jentitytest.test.unit.model.ReflectionUtilsTest");
        Annotation an = () -> BadComparisonCustom.class;
        reflectionUtils.initializeStrategyByAnnotation(an, BadCheck.class);

    }

    @Test
    public void reflectionutils_initializeStrategy_noMatchingConstructor() {
        expectedException.expect(StrategyInstantiationException.class);
        expectedException.expectMessage("Could not create instance of Strategy: class org.testmonkeys.jentitytest.test.unit.model.util.BadCheck");

        StrategyDefinition strategyDefinition = new StrategyDefinition();
        strategyDefinition.setStrategy("org.testmonkeys.jentitytest.test.unit.model.util.BadCheck");
        reflectionUtils.initializeStrategy(strategyDefinition);
    }


}
