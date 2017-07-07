package org.testmonkeys.jentitytest.exceptions;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.Comparator;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;


public class ComparatorInstantiationException extends RuntimeException {

    public ComparatorInstantiationException(Class<? extends Comparator> comparator, Annotation annotation,
                                            Exception e) {
        super(MessageFormat.format(Resources.getString(Resources.err_creating_comparator_for_annotation),comparator,
                annotation.getClass().getName(), e));
    }
}
