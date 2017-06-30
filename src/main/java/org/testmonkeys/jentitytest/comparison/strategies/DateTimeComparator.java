package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.DateTimeComparison;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class DateTimeComparator extends AbstractComparator {

    private int delta;
    private ChronoUnit unit = ChronoUnit.NANOS;

    public DateTimeComparator() {
        this.registerPreConditionalCheck(new NullConditionalCheck());
    }

    public DateTimeComparator(DateTimeComparison annotation) {
        super(annotation);
        this.registerPreConditionalCheck(new NullConditionalCheck());
        this.setDelta(annotation.delta());
        this.setUnit(annotation.unit());
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public void setUnit(ChronoUnit unit) {
        this.unit = unit;
    }

    @Override
    protected ResultSet computeComparison(Object a, Object e, ComparisonContext context) {
        Temporal actualValue;
        Temporal expectedValue;


        try {
            actualValue = (Temporal) a;
            expectedValue = (Temporal) e;
        } catch (ClassCastException castException) {
            throw new JEntityTestException("Expected and Actual values must be of type " + Temporal.class.getName(),
                    castException);
        }

        boolean passed = Math.abs(actualValue.until(expectedValue, this.unit)) <= this.delta;
        return new ResultSet().with(passed, context, a, e);
    }

    @Override
    protected String describe() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        if (this.delta == 0)
            sb.append(" with precision up to ").append(this.unit);
        else
            sb.append(" with delta ").append(this.delta).append(" ").append(this.unit);
        return sb.toString();
    }
}
