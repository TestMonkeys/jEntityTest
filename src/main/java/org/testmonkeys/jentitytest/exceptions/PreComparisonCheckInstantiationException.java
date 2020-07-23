package org.testmonkeys.jentitytest.exceptions;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.abortConditions.AbstractAbortCondition;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;


public class PreComparisonCheckInstantiationException extends RuntimeException {

    public PreComparisonCheckInstantiationException(Class<? extends AbstractAbortCondition> comparator, Annotation annotation,
                                                    Exception e) {
        super(MessageFormat.format(Resources.getString(Resources.err_creating_comparator_for_annotation),comparator,
                annotation.getClass().getName(), e));
    }
}
