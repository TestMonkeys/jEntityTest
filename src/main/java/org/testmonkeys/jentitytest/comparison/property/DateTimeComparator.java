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
    private int delta;
    private ChronoUnit unit = ChronoUnit.NANOS;

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public ChronoUnit getUnit() {
        return unit;
    }

    public void setUnit(ChronoUnit unit) {
        this.unit = unit;
    }

    @Override
    protected ComparisonResult computeSingleComparison(Object a, Object e, ComparisonContext context) {
        ComparisonResult result = new ComparisonResult(false, context, a, e);

        NullComparisonResult nullComparisonResult = this.nullComparatorHelper.compareOnNulls(a, e, context);
        if (!nullComparisonResult.isPassed() || nullComparisonResult.stopComparison()) {
            return nullComparisonResult;
        }

        Temporal actualValue;
        Temporal expectedValue;

//        if (!(a instanceof Temporal) && !(e instanceof Temporal))
//            throw new JEntityTestException("Expected and Actual values must of type " + Temporal.class.getName());
        try {
            actualValue = (Temporal) a;
            expectedValue = (Temporal) e;
        } catch (ClassCastException exception) {
            throw new JEntityTestException("Expected and Actual values must of type ", exception);
        }
        boolean passed = false;

        if (actualValue != null && expectedValue != null)
            passed = Math.abs(actualValue.until(expectedValue, this.unit)) <= this.delta;

        result.setPassed(passed);
        return result;
    }
}
