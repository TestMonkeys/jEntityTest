package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public class ConditionalCheckResult extends ComparisonResult {

    private boolean stopComparison;

    public ConditionalCheckResult(boolean passed, ComparisonContext context, String comparator, Object actual, Object
            expected) {
        super(passed, context, comparator, actual, expected);
    }

    public boolean stopComparison() {
        return stopComparison;
    }

    public void setStopComparison(boolean stopComparison) {
        this.stopComparison = stopComparison;
    }
}
