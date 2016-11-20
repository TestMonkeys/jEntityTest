package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;

public interface ConditionalCheck {

    ConditionalCheckResult runCheck(Object actual, Object expected, ComparisonContext context);
}
