package org.testmonkeys.jentitytest.comparison.result;


@SuppressWarnings("ClassWithTooManyDependents")
public enum Status {
    Passed,
    Failed;

    @SuppressWarnings("BooleanParameter")
    public static Status valueOf(boolean passed) {
        if (passed)
            return Passed;
        else
            return Failed;
    }
}
