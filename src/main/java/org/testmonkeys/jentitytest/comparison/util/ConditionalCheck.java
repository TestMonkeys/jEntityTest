package org.testmonkeys.jentitytest.comparison.util;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public interface ConditionalCheck {

    ConditionalCheckResult runCheck(Object actual, Object expected, ComparisonContext context);
}
