package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public class ComparisonResult {
    private ComparisonContext comparisonContext;
    private Object expected;
    private Object actual;
    private boolean passed;

    public ComparisonResult(boolean passed, ComparisonContext context, Object actual, Object
            expected) {
        setPassed(passed);
        setComparisonContext(context);
        setActual(actual);
        setExpected(expected);
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void setComparisonContext(ComparisonContext comparisonContext) {
        this.comparisonContext = comparisonContext;
    }

    public Object getExpected() {
        return expected;
    }

    public void setExpected(Object expected) {
        this.expected = expected;
    }

    public Object getActual() {
        return actual;
    }

    public void setActual(Object actual) {
        this.actual = actual;
    }


    public ComparisonContext getComparisonContext() {
        return comparisonContext;
    }
}
