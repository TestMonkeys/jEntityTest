package org.testmonkeys.jentitytest.framework;

public class JEntityTestException extends RuntimeException {

    public JEntityTestException() {
        super();
    }

    public JEntityTestException(String message) {
        super(message);
    }

    public JEntityTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public JEntityTestException(Throwable cause) {
        super(cause);
    }
}
