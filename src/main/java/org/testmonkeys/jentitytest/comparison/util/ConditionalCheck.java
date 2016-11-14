package org.testmonkeys.jentitytest.comparison.util;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public interface ConditionalCheck {

    NullComparisonResult runCheck(Object actual, Object expected, ComparisonContext context);
}
