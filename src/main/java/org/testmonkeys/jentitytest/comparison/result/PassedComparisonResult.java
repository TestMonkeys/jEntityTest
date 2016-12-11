package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public class PassedComparisonResult extends ComparisonResult {

    public PassedComparisonResult(ComparisonContext context, String comparator, Object actual, Object expected) {
        super(true, context, comparator, actual, expected);
    }
}
