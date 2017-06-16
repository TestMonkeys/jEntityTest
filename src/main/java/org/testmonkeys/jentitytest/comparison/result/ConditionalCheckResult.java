package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public class ConditionalCheckResult extends ComparisonResult {

    private boolean comparisonFinished;

    public ConditionalCheckResult(boolean passed, ComparisonContext context, Object actual, Object
            expected) {
        super(passed, context, actual, expected);
    }

    public boolean isComparisonFinished() {
        return comparisonFinished;
    }

    public void stopComparison() {
        comparisonFinished = true;
    }
}
