package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.util.NullComparator;

public class SimpleTypeComparator extends SingleResultComparator {

    public SimpleTypeComparator() {
        registerPreConditionalCheck(new NullComparator());
    }

    @Override
    public ComparisonResult computeSingleComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        ComparisonResult result = new ComparisonResult(false, context, actualValue, expectedValue);
        result.setPassed(actualValue.equals(expectedValue));
        return result;
    }
}
