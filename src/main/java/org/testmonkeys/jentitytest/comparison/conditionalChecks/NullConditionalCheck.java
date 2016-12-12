package org.testmonkeys.jentitytest.comparison.conditionalChecks;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;

public class NullConditionalCheck implements ConditionalCheck {

    public ConditionalCheckResult runCheck(Object actual, Object expected, ComparisonContext context) {
        ConditionalCheckResult result = new ConditionalCheckResult(true, context, actual, expected);

        if (actual == null && expected == null) {
            result.setStopComparison(true);
            return result;
        }

        if (actual != null && expected != null)
            return result;

        result.setPassed(false);
        result.setExpected(expected == null ? "null" : "not null");
        result.setActual(actual == null ? "null" : "not null");
        return result;
    }
}
