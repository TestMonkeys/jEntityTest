package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

public class SimpleTypeComparator extends SingleResultComparator {

    public SimpleTypeComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }

    @Override
    public ComparisonResult computeSingleComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        ComparisonResult result = new ComparisonResult(false, context, actualValue, expectedValue);
        result.setPassed(actualValue.equals(expectedValue));
        return result;
    }
}
