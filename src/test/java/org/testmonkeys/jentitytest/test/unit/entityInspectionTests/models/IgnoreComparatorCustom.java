package org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;

import java.beans.PropertyDescriptor;

public class IgnoreComparatorCustom extends SingleResultComparator {

    @Override
    protected ComparisonResult computeComparison(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) {
        ComparisonResult result = new ComparisonResult();
        result.setPassed(true);
        Object actualValue = getPropertyValue(property, actual);
        Object expectedValue = getPropertyValue(property, expected);

        result.setComparisonContext(context);
        result.setActual(actualValue);
        result.setExpected(expectedValue);
        return result;
    }
}
