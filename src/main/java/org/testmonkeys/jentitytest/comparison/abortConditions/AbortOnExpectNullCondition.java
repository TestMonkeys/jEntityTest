package org.testmonkeys.jentitytest.comparison.abortConditions;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;

import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

public class AbortOnExpectNullCondition extends AbstractAbortCondition {

    public ConditionalCheckResult runCheck(Object actual, Object expected, ComparisonContext context) {
        ConditionalCheckResult result = new ConditionalCheckResult(Passed, context, actual, expected);

        if (expected == null) {
            result.stopComparison();
        }
        return result;
    }
}
