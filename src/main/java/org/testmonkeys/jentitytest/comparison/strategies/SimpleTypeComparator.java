package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;

public class SimpleTypeComparator extends AbstractComparator {

    public SimpleTypeComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }

    @Override
    public ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        Status status = Status.valueOf(actual.equals(expected));

        return new ResultSet().with(status, context, actual, expected);
    }

}
