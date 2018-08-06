package org.testmonkeys.jentitytest.exceptions;


public class JEntityModelException extends RuntimeException {
    public JEntityModelException(String message) {
        super(message);
    }

    public JEntityModelException(String message, Throwable cause) {
        super(message, cause);
    }
}
