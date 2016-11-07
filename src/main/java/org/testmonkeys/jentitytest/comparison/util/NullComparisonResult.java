package org.testmonkeys.jentitytest.comparison.util;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

public class NullComparisonResult extends ComparisonResult {

    private boolean stopComparison;

    public NullComparisonResult(boolean passed, ComparisonContext context, Object actual, Object expected) {
        super(passed, context, actual, expected);
    }

    public boolean stopComparison() {
        return stopComparison;
    }

    public void setStopComparison(boolean stopComparison) {
        this.stopComparison = stopComparison;
    }
}
