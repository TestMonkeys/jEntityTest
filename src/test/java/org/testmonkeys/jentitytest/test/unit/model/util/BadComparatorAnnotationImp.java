package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.test.unit.model.AnnotationToComparatorDictionaryTest.BadComparatorAnnotation;

import java.lang.annotation.Annotation;

/**
 * Created by cpascal on 6/21/2017.
 */
public class BadComparatorAnnotationImp implements BadComparatorAnnotation {

    @Override
    public int value() {
        return 0;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return BadComparatorAnnotation.class;
    }
}
