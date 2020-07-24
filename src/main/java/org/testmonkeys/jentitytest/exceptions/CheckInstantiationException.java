package org.testmonkeys.jentitytest.exceptions;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.AbstractCheck;
import org.testmonkeys.jentitytest.comparison.abortConditions.AbstractAbortCondition;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;


public class CheckInstantiationException extends RuntimeException {

    public CheckInstantiationException(Class<? extends AbstractCheck> comparator, Annotation annotation,
                                       Exception e) {
        super(MessageFormat.format(Resources.getString(Resources.err_creating_comparator_for_annotation),comparator,
                annotation.getClass().getName(), e));
    }
}
