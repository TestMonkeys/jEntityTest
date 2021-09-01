package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.comparison.result.Status;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Base class for Abort Condition Checks, acts as a wrapper and makes all abort conditions implement the strategy
 * pattern
 */
public abstract class AbstractCheck {

    private Map<String, Object> parameters;

    /**
     * @param property property on which the check will run
     * @param actual   actual parent object of the property
     * @param expected expected parent object of the property
     * @param context  comparison context
     * @return conditional check result
     * @throws JEntityTestException if comparison is impossible
     */
    public ConditionalCheckResult check(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) {
        Object actualValue = getPropertyValue(property, actual);
        Object expectedValue = getPropertyValue(property, expected);

        if (context.isRecursive(actualValue))
            return new ConditionalCheckResult(Status.Passed, context, actualValue, expectedValue);
        context.setActualObj(actualValue);
        return runCheck(actualValue, expectedValue, context);
    }

    protected abstract ConditionalCheckResult runCheck(Object actualValue, Object expectedValue, ComparisonContext context);

    @SuppressWarnings("WeakerAccess")
    protected Object getPropertyValue(PropertyDescriptor property, Object obj) {
        try {
            return property.getReadMethod().invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new JEntityTestException(Resources.getString(Resources.err_could_not_read_property), e);
        }
    }

}
