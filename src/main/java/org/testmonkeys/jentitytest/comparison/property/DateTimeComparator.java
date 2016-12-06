package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class DateTimeComparator extends SingleResultComparator {

    private int delta;
    private ChronoUnit unit = ChronoUnit.NANOS;

    public DateTimeComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public void setUnit(ChronoUnit unit) {
        this.unit = unit;
    }

    @Override
    protected ComparisonResult computeSingleComparison(Object a, Object e, ComparisonContext context) {
        ComparisonResult result = new ComparisonResult(false, context, a, e);

        Temporal actualValue;
        Temporal expectedValue;

        try {
            actualValue = (Temporal) a;
            expectedValue = (Temporal) e;
        } catch (ClassCastException castException) {
            throw new JEntityTestException("Expected and Actual values must be of type " + Temporal.class.getName(),
                    castException);
        }

        result.setPassed(Math.abs(actualValue.until(expectedValue, unit)) <= delta);
        return result;
    }


}
