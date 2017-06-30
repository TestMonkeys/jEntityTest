package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;

public class SimpleTypeComparator extends AbstractComparator {

    public SimpleTypeComparator() {
        this.registerPreConditionalCheck(new NullConditionalCheck());
    }

    @Override
    public ResultSet computeComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        boolean passed=actualValue.equals(expectedValue);

        return new ResultSet().with(passed, context, actualValue, expectedValue);
    }
}
