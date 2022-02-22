package org.testmonkeys.jentitytest.test.unit.model;

import org.hamcrest.junit.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testmonkeys.jentitytest.exceptions.JEntityModelException;
import org.testmonkeys.jentitytest.exceptions.StrategyInstantiationByAnnotationException;
import org.testmonkeys.jentitytest.model.AnnotationToComparatorDictionary;
import org.testmonkeys.jentitytest.test.unit.model.util.*;

import java.beans.IntrospectionException;
import java.lang.annotation.*;

/**
 * Created by cpascal on 6/21/2017.
 */
public class AnnotationToComparatorDictionaryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private AnnotationToComparatorDictionary comparisonMap;

    /**
     * initialization of EntityInspector,
     * generation of comparison model for ModelMultiAnnotated.class,
     * retrieval of comparable properties
     *
     * @throws IntrospectionException
     */
    @Before
    public void background() throws IntrospectionException {
        comparisonMap = AnnotationToComparatorDictionary.getInstance();
        comparisonMap.setComparatorForAnnotation(BadComparator.class, BadComparisonCustom.class);
    }

    /**
     * Check comparisonMap will throw exception if it has no comparator for annotation
     */
    @Test
    public void unit_modelToComparison_noMappingForAnnotation() throws Throwable {
        expectedException.expect(JEntityModelException.class);
        expectedException.expectMessage("There is no comparator defined for annotation " +
                "org.testmonkeys.jentitytest.test.unit.model.util.SimpleAnnotation");

        Annotation an = () -> SimpleAnnotation.class;
        comparisonMap.getComparatorForAnnotation(an);
    }

    /**
     * Check comparisonMap will throw exception if annotation provided is null
     */
    @Test
    public void unit_modelToComparison_nullAnnotation() throws Throwable {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Annotation can not be null");

        comparisonMap.getComparatorForAnnotation(null);
    }


    /**
     * Invalid registration of null Comparator
     */
    @Test
    public void unit_modelToComparison_registrationNullComparator() throws Throwable {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Comparator can not be null");

        comparisonMap.setComparatorForAnnotation(null, SimpleAnnotation.class);
    }

    /**
     * Invalid registration of null Annotation
     */
    @Test
    public void unit_modelToComparison_registrationNullAnnotation() throws Throwable {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Annotation can not be null");

        comparisonMap.setComparatorForAnnotation(IgnoreComparatorCustom.class, null);
    }

    @Test
    public void annotationToComparatoryDictionary_bad_initCtrAnn_privateConstructor() {
        expectedException.expect(StrategyInstantiationByAnnotationException.class);
        AnnotationToComparatorDictionary dictionary = AnnotationToComparatorDictionary.getInstance();
        dictionary.setComparatorForAnnotation(BadComparatorPrivateConstructor.class, BadComparatorAnnotation.class);
        dictionary.getComparatorForAnnotation(new BadComparatorAnnotationImp());
    }

    @Test
    public void annotationToComparatoryDictionary_bad_initCtrAnn_abstractComparator() {
        expectedException.expect(StrategyInstantiationByAnnotationException.class);
        AnnotationToComparatorDictionary dictionary = AnnotationToComparatorDictionary.getInstance();
        dictionary.setComparatorForAnnotation(BadComparatorAbstract.class, BadComparatorAnnotation.class);
        dictionary.getComparatorForAnnotation(new BadComparatorAnnotationImp());
    }

    @Test
    public void annotationToComparatoryDictionary_bad_initCtrAnn_exceptionTrhown() {
        expectedException.expect(StrategyInstantiationByAnnotationException.class);
        AnnotationToComparatorDictionary dictionary = AnnotationToComparatorDictionary.getInstance();
        dictionary.setComparatorForAnnotation(BadComparatorErrInConstructor.class, BadComparatorAnnotation.class);
        dictionary.getComparatorForAnnotation(new BadComparatorAnnotationImp());
    }

    @Test
    public void annotationToComparatoryDictionary_bad_initCtrDefault_privateConstructor() {
        expectedException.expect(StrategyInstantiationByAnnotationException.class);
        AnnotationToComparatorDictionary dictionary = AnnotationToComparatorDictionary.getInstance();
        dictionary.setComparatorForAnnotation(BadComparatorPrivateDefaultConstructor.class, BadComparatorAnnotation.class);
        dictionary.getComparatorForAnnotation(new BadComparatorAnnotationImp());
    }

    @Test
    public void annotationToComparatoryDictionary_bad_initCtrDefault_abstractComparator() {
        expectedException.expect(StrategyInstantiationByAnnotationException.class);
        AnnotationToComparatorDictionary dictionary = AnnotationToComparatorDictionary.getInstance();
        dictionary.setComparatorForAnnotation(BadComparatorAbstractDefaultCtr.class, BadComparatorAnnotation.class);
        dictionary.getComparatorForAnnotation(new BadComparatorAnnotationImp());
    }

    @Test
    public void annotationToComparatoryDictionary_bad_initCtrDefault_exceptionTrhown() {
        expectedException.expect(StrategyInstantiationByAnnotationException.class);
        AnnotationToComparatorDictionary dictionary = AnnotationToComparatorDictionary.getInstance();
        dictionary.setComparatorForAnnotation(BadComparatorErrInDefaultConstructor.class, BadComparatorAnnotation.class);
        dictionary.getComparatorForAnnotation(new BadComparatorAnnotationImp());
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD})
    public @interface BadComparatorAnnotation {
        int value();
    }

}
