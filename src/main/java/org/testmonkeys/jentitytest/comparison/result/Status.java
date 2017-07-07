package org.testmonkeys.jentitytest.comparison.result;

/**
 * Created by cpascal on 7/6/2017.
 */
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
