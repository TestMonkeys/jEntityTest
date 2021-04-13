package org.testmonkeys.jentitytest.comparison.strategies;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.DateTimeComparison;

import java.text.MessageFormat;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import static org.testmonkeys.jentitytest.Resources.*;

@Slf4j
public class DateTimeComparator extends AbstractComparator {

    @Getter
    @Setter
    private int delta;

    private ChronoUnit chronoUnit = ChronoUnit.NANOS;
    @Getter
    private String unit = "NANOS";

    public DateTimeComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }

    public DateTimeComparator(DateTimeComparison annotation) {
        registerPreConditionalCheck(new NullConditionalCheck());
        delta = annotation.delta();
        chronoUnit = annotation.unit();
    }

    public void setUnit(String unit) {
        this.chronoUnit = ChronoUnit.valueOf(unit);
        this.unit = unit;
    }

    @Override
    protected ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        Temporal actualValue;
        Temporal expectedValue;


        try {
            actualValue = (Temporal) actual;
            expectedValue = (Temporal) expected;
        } catch (ClassCastException castException) {
            throw new JEntityTestException(MessageFormat.format(
                    Resources.getString(err_actual_and_expected_must_be_of_type), Temporal.class.getName()),
                    castException);
        }

        Status status = Status.valueOf(Math.abs(actualValue.until(expectedValue, chronoUnit)) <= delta);
        return new ResultSet().with(status, context, actual, expected);
    }

    @Override
    protected String describe() {
        if (delta == 0)
            return MessageFormat.format(Resources.getString(desc_datetime_precision),
                    getClass().getSimpleName(), chronoUnit);
        else
            return MessageFormat.format(Resources.getString(desc_datetime_delta), getClass().getSimpleName(), delta, chronoUnit);
    }
}
