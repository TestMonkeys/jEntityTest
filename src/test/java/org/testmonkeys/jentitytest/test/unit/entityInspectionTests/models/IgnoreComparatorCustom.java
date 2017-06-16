package org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;

public class IgnoreComparatorCustom extends AbstractComparator {

    @Override
    protected ResultSet computeComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        return new ResultSet(true,context, actualValue, expectedValue);
    }
}
