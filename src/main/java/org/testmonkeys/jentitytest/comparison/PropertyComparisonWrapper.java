package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class PropertyComparisonWrapper {

    private final Comparator comparator;

    public PropertyComparisonWrapper(Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     * @param property
     * @param actual
     * @param expected
     * @param context
     * @return
     * @throws JEntityTestException
     */
    public List<ComparisonResult> areEqual(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) {
        List<ComparisonResult> resultList = new LinkedList<>();

        Object actualValue = this.getPropertyValue(property, actual);
        Object expectedValue = this.getPropertyValue(property, expected);

        if (context.isRecursive(actualValue))
            return resultList;
        context.setActualObj(actualValue);
        resultList.addAll(this.comparator.compare(actualValue, expectedValue, context));

        return resultList;
    }

    protected Object getPropertyValue(PropertyDescriptor property, Object obj) {
        try {
            return property.getReadMethod().invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new JEntityTestException("Could not read property from object", e);
        }
    }

    public Comparator getComparator() {
        return this.comparator;
    }
}
