package org.testmonkeys.jentitytest.exceptions;

import org.testmonkeys.jentitytest.Resources;

import java.text.MessageFormat;


public class StrategyInstantiationException extends RuntimeException {

    public StrategyInstantiationException(Class<?> clazz, Exception e) {
        super(MessageFormat.format(Resources.getString(Resources.err_creating_strategy), clazz, e));
    }
}
