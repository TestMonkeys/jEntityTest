package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;

import java.beans.PropertyDescriptor;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class DateTimeComparator extends SingleResultComparator {

    private int delta = 0;
    private ChronoUnit unit = ChronoUnit.SECONDS;

    @Override
    protected ComparisonResult computeComparison(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) {

        Temporal actualValue = (Temporal) getPropertyValue(property, actual);
        Temporal expectedValue = (Temporal) getPropertyValue(property, expected);

        ComparisonResult result = new ComparisonResult();
        boolean passed = false;

        if (actual == null && expectedValue == null)
            passed = true;
        else if (actual != null && expectedValue != null)
            passed = actualValue.until(expectedValue, unit) <= delta;

        result.setPassed(passed);
        result.setComparisonContext(context);
        result.setActual(actualValue);
        result.setExpected(expectedValue);
        return result;
    }
}
