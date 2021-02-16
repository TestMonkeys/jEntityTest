package org.testmonkeys.jentitytest.exceptions;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.AbstractCheck;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;


public class CheckInstantiationByAnnotationException extends RuntimeException {

    public CheckInstantiationByAnnotationException(Class<? extends AbstractCheck> comparator, Annotation annotation,
                                                   Exception e) {
        super(MessageFormat.format(Resources.getString(Resources.err_creating_check_for_annotation), comparator,
                annotation.getClass().getName(), e));
    }
}
