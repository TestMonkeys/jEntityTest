package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.util.NullComparator;
import org.testmonkeys.jentitytest.comparison.util.NullComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class DateTimeComparator extends SingleResultComparator {

    private final NullComparator nullComparatorHelper = new NullComparator();

    private int delta = 0;
    private ChronoUnit unit = ChronoUnit.NANOS;

    @Override
    protected ComparisonResult computeSingleComparison(Object a, Object e, ComparisonContext context) {
        ComparisonResult result = new ComparisonResult(false, context, a, e);

        NullComparisonResult nullComparisonResult = nullComparatorHelper.compareOnNulls(a, e, context);
        if (!nullComparisonResult.isPassed() || nullComparisonResult.stopComparison()) {
            return nullComparisonResult;
        }

        Temporal actualValue;
        Temporal expectedValue;

        try {
            actualValue = (Temporal) a;
            expectedValue = (Temporal) e;
        } catch (ClassCastException castException) {
            throw new JEntityTestException("Expected and Actual values must of type " + Temporal.class.getName(),
                    castException);
        }
        boolean passed = false;

        if (actualValue != null && expectedValue != null)
            passed = Math.abs(actualValue.until(expectedValue, unit)) <= delta;

        result.setPassed(passed);
        return result;
    }
}
