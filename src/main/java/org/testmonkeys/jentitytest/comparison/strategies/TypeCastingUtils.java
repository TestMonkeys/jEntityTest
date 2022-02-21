package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.text.MessageFormat;
import java.time.temporal.Temporal;
import java.util.Collection;

import static org.testmonkeys.jentitytest.Resources.err_actual_and_expected_must_be_generic_Collections;
import static org.testmonkeys.jentitytest.Resources.err_actual_and_expected_must_be_of_type;

public class TypeCastingUtils {

    public String toString(Object o) {
        String value;
        try {
            value = (String) o;
        } catch (ClassCastException castException) {
            throw new JEntityTestException(MessageFormat.format(
                    Resources.getString(Resources.err_actual_and_expected_must_be_of_type), String.class.getName()),
                    castException);
        }
        return value;
    }

    public Temporal toTemporal(Object o) {
        Temporal value;
        try {
            value = (Temporal) o;
        } catch (ClassCastException castException) {
            throw new JEntityTestException(MessageFormat.format(
                    Resources.getString(err_actual_and_expected_must_be_of_type), Temporal.class.getName()),
                    castException);
        }
        return value;
    }

    public Collection<?> toCollection(Object object) {
        try {
            return (Collection<?>) object;
        } catch (ClassCastException ex) {
            throw new JEntityTestException(Resources.getString(err_actual_and_expected_must_be_generic_Collections), ex);
        }
    }
}
