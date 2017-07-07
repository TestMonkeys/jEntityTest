package org.testmonkeys.jentitytest.comparison.conditionalChecks;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.PreComparisonCheck;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;

import static org.testmonkeys.jentitytest.Resources.NULL;
import static org.testmonkeys.jentitytest.Resources.NOT_NULL;
import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;
import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

@SuppressWarnings("VariableNotUsedInsideIf")
public class NullConditionalCheck implements PreComparisonCheck {

    public ConditionalCheckResult runCheck(Object actual, Object expected, ComparisonContext context) {
        ConditionalCheckResult result = new ConditionalCheckResult(Passed, context, actual, expected);

        if ((actual == null) && (expected == null)) {
            result.stopComparison();
            return result;
        }

        if ((actual != null) && (expected != null))
            return result;

        result.setStatus(Failed);
        result.setExpected(Resources.getString((expected == null) ? NULL : NOT_NULL));
        result.setActual(Resources.getString((actual == null) ? NULL : NOT_NULL));
        return result;
    }
}
