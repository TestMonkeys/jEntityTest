package org.testmonkeys.jentitytest.exceptions;

@SuppressWarnings("UncheckedExceptionClass")
public class JEntityTestException extends RuntimeException {

    public JEntityTestException(String message) {
        super(message);
    }

    public JEntityTestException(String message, Throwable cause) {
        super(message, cause);
    }
}
