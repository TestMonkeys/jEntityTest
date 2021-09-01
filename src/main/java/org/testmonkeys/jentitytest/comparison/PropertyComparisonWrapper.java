package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class PropertyComparisonWrapper {

    private final Comparator comparator;

    public PropertyComparisonWrapper(Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     * @param property propertyDescriptor for the property
     * @param actual   actual object
     * @param expected expected object
     * @param context  context of this comparison
     * @return result set of the comparison
     * @throws JEntityTestException if comparison is impossible
     */
    public ResultSet compare(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) {
        ResultSet resultList = new ResultSet();

        Object actualValue = getPropertyValue(property, actual);
        Object expectedValue = getPropertyValue(property, expected);

        if (context.isRecursive(actualValue))
            return resultList;
        context.setActualObj(actualValue);
        resultList.addAll(comparator.compare(actualValue, expectedValue, context));

        return resultList;
    }

    @SuppressWarnings("WeakerAccess")
    protected Object getPropertyValue(PropertyDescriptor property, Object obj) {
        try {
            return property.getReadMethod().invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new JEntityTestException(Resources.getString(Resources.err_could_not_read_property), e);
        }
    }

    public Comparator getComparator() {
        return comparator;
    }
}
