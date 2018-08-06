package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public class ConditionalCheckResult extends ComparisonResult {

    private boolean comparisonFinished;

    public ConditionalCheckResult(Status status, ComparisonContext context, Object actual, Object
            expected) {
        super(status, context, actual, expected);
    }

    public boolean isComparisonFinished() {
        return comparisonFinished;
    }

    public void stopComparison() {
        comparisonFinished = true;
    }
}
