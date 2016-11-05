package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public class PassedComparisonResult extends ComparisonResult {

    public PassedComparisonResult(ComparisonContext context, Object actual, Object expected) {
        super(true, context, actual, expected);
    }
}
