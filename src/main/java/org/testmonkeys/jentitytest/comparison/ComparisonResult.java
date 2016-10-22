package org.testmonkeys.jentitytest.comparison;

public class ComparisonResult {
    private ComparisonContext comparisonContext;
    private Object expected;
    private Object actual;

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    private boolean passed;

    public void setComparisonContext(ComparisonContext comparisonContext) {
        this.comparisonContext = comparisonContext;
    }

    public ComparisonContext getContext() {
        return this.comparisonContext;
    }

    public void setExpected(Object expected) {
        this.expected = expected;
    }

    public Object getExpected() {
        return this.expected;
    }

    public void setActual(Object actual) {
        this.actual = actual;
    }

    public Object getActual() {
        return this.actual;
    }
}
