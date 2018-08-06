package org.testmonkeys.jentitytest.test.unit.model.util;


import java.lang.reflect.InvocationTargetException;

public class Model {
    private String illegalAccess;

    private String invocationTarget;

    public String getIllegalAccess() throws IllegalAccessException {
        throw new IllegalAccessException("illegal access exception");
    }

    public String getInvocationTarget() throws InvocationTargetException {
        throw new InvocationTargetException(new NullPointerException("null pointer"));
    }
}
