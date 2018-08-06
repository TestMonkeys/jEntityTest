package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public class ComparisonResult {
    private final ComparisonContext comparisonContext;
    private Object expected;
    private Object actual;
    private Status status;

    public ComparisonResult(Status status, ComparisonContext context, Object actual, Object
            expected) {
        this.status = status;
        comparisonContext = context;
        this.actual = actual;
        this.expected = expected;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
