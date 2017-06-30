package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;

public class IgnoreComparator extends AbstractComparator {

    public IgnoreComparator() {
    }

    @Override
    protected ResultSet computeComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        return new ResultSet().with(true, context, actualValue, expectedValue);
    }
}
