package org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.PassedComparisonResult;

public class IgnoreComparatorCustom extends SingleResultComparator {

    @Override
    protected ComparisonResult computeSingleComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        return new PassedComparisonResult(context, this.getClass().getSimpleName(), actualValue, expectedValue);
    }
}
