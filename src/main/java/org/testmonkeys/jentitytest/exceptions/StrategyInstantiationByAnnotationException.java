package org.testmonkeys.jentitytest.exceptions;

import org.testmonkeys.jentitytest.Resources;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;


public class StrategyInstantiationByAnnotationException extends RuntimeException {

    public StrategyInstantiationByAnnotationException(Class<?> strategy, Annotation annotation,
                                                      Exception e) {
        super(MessageFormat.format(Resources.getString(Resources.err_creating_strategy_for_annotation), strategy,
                annotation.getClass().getName(), e));
    }
}
