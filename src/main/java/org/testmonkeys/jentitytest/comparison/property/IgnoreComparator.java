package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;

public class IgnoreComparator extends AbstractComparator {

    @Override
    protected ResultSet computeComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        return new ResultSet(true,context, actualValue,  expectedValue);
    }
}
