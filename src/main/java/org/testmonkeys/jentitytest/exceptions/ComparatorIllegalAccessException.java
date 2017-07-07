package org.testmonkeys.jentitytest.exceptions;

import org.testmonkeys.jentitytest.comparison.Comparator;

import java.lang.annotation.Annotation;

/**
 * Created by cpascal on 6/21/2017.
 */
public class ComparatorIllegalAccessException extends JEntityTestException {

    public ComparatorIllegalAccessException(Class<? extends Comparator> comparator, Annotation annotation, Exception e) {
        super("Could not create instance of Comparator " + comparator.getName() + " for annotation " + annotation.getClass().getName(), e);
    }
}
