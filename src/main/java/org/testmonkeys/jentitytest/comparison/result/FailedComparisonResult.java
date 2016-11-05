package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public class FailedComparisonResult extends ComparisonResult {

    public FailedComparisonResult(ComparisonContext context, Object actual, Object expected) {
        super(false, context, actual, expected);
    }
}
