package org.testmonkeys.jentitytest.test.unit.entityInspectionTests;

import org.junit.Before;
import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.property.IgnoreComparator;
import org.testmonkeys.jentitytest.framework.JEntityTestException;
import org.testmonkeys.jentitytest.inspect.EntityInspector;
import org.testmonkeys.jentitytest.inspect.ModelToComparisonMap;
import org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models.ModelMultiAnnotated;
import org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models.SimpleAnnotation;
import sun.java2d.pipe.SpanShapeRenderer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
    }

    /**
     * Check comparisonMap will throw exception if it has no comparator for annotation
     */
//    @Test
//    public void modelToComparison_noMappingForAnnotation() throws Throwable {
//        Annotation an=new Annotation() {
//            @Override
//            public Class<? extends Annotation> annotationType() {
//                return SimpleAnnotation.class;
//            }
//        };
//        comparisonMap.getComparatorForAnnotation(an);
//    }



}
