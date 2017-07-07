package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;

import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

public class IgnoreComparatorCustom extends AbstractComparator {

    @Override
    protected ResultSet computeComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        return new ResultSet().with(Passed, context, actualValue, expectedValue);
    }
}
