package org.testmonkeys.jentitytest.comparison.validations;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;

import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;
import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

public class NotNullValidator extends AbstractValidation {

    public ConditionalCheckResult runCheck(Object actual, Object expected, ComparisonContext context) {
        context.setComparatorDetails("NotNullValidator");
        ConditionalCheckResult result = new ConditionalCheckResult(Passed, context, actual, expected);

        if (actual == null) {
            result.setStatus(Failed);

        }
        return result;
    }
}
