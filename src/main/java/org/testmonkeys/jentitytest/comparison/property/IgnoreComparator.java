package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.PassedComparisonResult;

public class IgnoreComparator extends SingleResultComparator {

    @Override
    protected ComparisonResult computeSingleComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        return new PassedComparisonResult(context, actualValue, expectedValue);
    }
}
