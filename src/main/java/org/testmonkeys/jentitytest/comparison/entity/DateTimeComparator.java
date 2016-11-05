package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class DateTimeComparator extends SingleResultComparator {

    private int delta = 0;
    private ChronoUnit unit = ChronoUnit.SECONDS;

    @Override
    protected ComparisonResult computeComparison(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) {
        Object a = getPropertyValue(property, actual);
        Object e = getPropertyValue(property, expected);
        ComparisonResult result = new ComparisonResult();

        result.setComparisonContext(context);
        result.setActual(a);
        result.setExpected(e);

        if (a == null && e == null) {
            result.setPassed(true);
            return result;
        }

        if (!(a instanceof Temporal) && !(e instanceof Temporal))
            throw new JEntityTestException("Expected and Actual values must of type " + Temporal.class.getName());

        Temporal actualValue = (Temporal) a;
        Temporal expectedValue = (Temporal) e;

        boolean passed = false;

        if (actualValue != null && expectedValue != null)
            passed = actualValue.until(expectedValue, unit) <= delta;

        result.setPassed(passed);
        return result;
    }
}
