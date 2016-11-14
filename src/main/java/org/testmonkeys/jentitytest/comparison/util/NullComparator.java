package org.testmonkeys.jentitytest.comparison.util;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public class NullComparator implements ConditionalCheck {

    public NullComparisonResult runCheck(Object actual, Object expected, ComparisonContext context) {
        NullComparisonResult result = new NullComparisonResult(true, context, actual, expected);

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
