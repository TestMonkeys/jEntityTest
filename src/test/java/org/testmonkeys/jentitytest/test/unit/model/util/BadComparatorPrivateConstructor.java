package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.test.unit.model.AnnotationToComparatorDictionaryTest.BadComparatorAnnotation;

/**
 * Created by cpascal on 6/21/2017.
 */
public class BadComparatorPrivateConstructor extends AbstractComparator {

    private BadComparatorPrivateConstructor(BadComparatorAnnotation a) {

    }

    @Override
    protected ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        return null;
    }
}
