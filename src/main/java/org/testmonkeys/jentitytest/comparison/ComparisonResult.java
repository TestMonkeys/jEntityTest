package org.testmonkeys.jentitytest.comparison;

public class ComparisonResult {
    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    private boolean passed;

}
