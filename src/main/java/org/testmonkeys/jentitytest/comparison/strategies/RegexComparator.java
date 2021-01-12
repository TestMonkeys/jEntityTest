package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.StringComparison;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegexComparator extends AbstractComparator {

    public RegexComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }


    public ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        String actualValue;
        String expectedValue;

        try {
            actualValue = (String) actual;
            expectedValue = (String) expected;
        } catch (ClassCastException castException) {
            throw new JEntityTestException(MessageFormat.format(
                    Resources.getString(Resources.err_actual_and_expected_must_be_of_type), String.class.getName()),
                    castException);
        }

        Status status = Status.valueOf(Pattern.matches(expectedValue,actualValue));

        return new ResultSet().with(status, context, actualValue, expectedValue);
    }

    @Override
    protected String describe() {
        return getClass().getSimpleName();
    }


}
