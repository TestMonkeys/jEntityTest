package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;

import java.util.regex.Pattern;

public class RegexComparator extends AbstractComparator {

    private final TypeCastingUtils typeCasting = new TypeCastingUtils();

    public RegexComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }


    public ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        String actualValue = typeCasting.toString(actual);
        String expectedValue = typeCasting.toString(expected);

        Status status = Status.valueOf(Pattern.matches(expectedValue, actualValue));
        return new ResultSet().with(status, context, actualValue, expectedValue);
    }

    @Override
    protected String describe() {
        return getClass().getSimpleName();
    }


}
