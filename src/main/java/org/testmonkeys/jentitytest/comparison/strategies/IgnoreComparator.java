package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;

import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

public class IgnoreComparator extends AbstractComparator {

    @Override
    protected ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        return new ResultSet().with(Passed, context, actual, expected);
    }
}
