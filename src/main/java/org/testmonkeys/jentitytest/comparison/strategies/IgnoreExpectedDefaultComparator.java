package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;

public class IgnoreExpectedDefaultComparator extends AbstractComparator {

    public IgnoreExpectedDefaultComparator() {

    }

    @Override
    public ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        Status status=Status.Passed;
        if (expected==null)
            status=Status.Skipped;

        return new ResultSet().with(status, context, actual, expected);
    }

}
