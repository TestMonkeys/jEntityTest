package org.testmonkeys.jentitytest.test.unit.entityInspectionTests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.framework.JEntityTestException;
import org.testmonkeys.jentitytest.inspect.ModelToComparisonMap;
import org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models.BadComparator;
import org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models.BadComparisonCustom;
import org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models.IgnoreComparatorCustom;
import org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models.SimpleAnnotation;

import java.beans.IntrospectionException;
import java.lang.annotation.Annotation;

/**
 * This class represents tests for comparison map
 */
public class ModelToComparisonMapTest {

    private ModelToComparisonMap comparisonMap;

    /**
     * initialization of EntityInspector,
     * generation of comparison model for ModelMultiAnnotated.class,
     * retrieval of comparable properties
     * @throws IntrospectionException
     */
    @Before
    public void background() throws IntrospectionException, JEntityTestException {
        comparisonMap= ModelToComparisonMap.getInstance();
        comparisonMap.setComparatorForAnnotation(BadComparator.class, BadComparisonCustom.class);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    /**
     * Check comparisonMap will throw exception if it has no comparator for annotation
     */
    @Test
    public void modelToComparison_noMappingForAnnotation() throws Throwable {
        expectedEx.expect(JEntityTestException.class);
        expectedEx.expectMessage("There is no comparator defined for annotation "+
        "org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models.SimpleAnnotation");

        Annotation an= () -> SimpleAnnotation.class;
        comparisonMap.getComparatorForAnnotation(an);
    }

    /**
     * Check comparisonMap will throw exception if annotation provided is null
     */
    @Test
    public void modelToComparison_nullAnnotation() throws Throwable {
        expectedEx.expect(JEntityTestException.class);
        expectedEx.expectMessage("Annotation can not be null");

        comparisonMap.getComparatorForAnnotation(null);
    }

    /**
     * Bad Comparator mapping test. Comparator was implemented with parameters in constructor
     */
    @Test
    public void modelToComparison_badComparatorImplementation() throws Throwable {
        expectedEx.expect(JEntityTestException.class);
        expectedEx.expectMessage("Could not create Comparator for annotation "+
                "org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models.BadComparisonCustom");

        Annotation an= () -> BadComparisonCustom.class;
        comparisonMap.getComparatorForAnnotation(an);
    }

    /**
     * Invalid registration of null Comparator
     */
    @Test
    public void modelToComparison_registrationNullComparator() throws Throwable {
        expectedEx.expect(JEntityTestException.class);
        expectedEx.expectMessage("Comparator can not be null");

        comparisonMap.setComparatorForAnnotation(null, SimpleAnnotation.class);
    }

    /**
     * Invalid registration of null Annotation
     */
    @Test
    public void modelToComparison_registrationNullAnnotation() throws Throwable {
        expectedEx.expect(JEntityTestException.class);
        expectedEx.expectMessage("Annotation can not be null");

        comparisonMap.setComparatorForAnnotation(IgnoreComparatorCustom.class,null);
    }
}
