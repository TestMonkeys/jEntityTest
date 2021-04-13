package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.comparison.AbstractCheck;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;

public class BadCheck extends AbstractCheck {

    public BadCheck(int param) {
    }

    @Override
    protected ConditionalCheckResult runCheck(Object actualValue, Object expectedValue, ComparisonContext context) {
        return null;
    }
}
