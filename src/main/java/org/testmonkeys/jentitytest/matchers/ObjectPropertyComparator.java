package org.testmonkeys.jentitytest.matchers;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class ObjectPropertyComparator {

    private Comparator comparator;

    public ObjectPropertyComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    public List<ComparisonResult> areEqual(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) throws JEntityTestException {
        List<ComparisonResult> resultList = new LinkedList<>();

        Object actualValue = getPropertyValue(property, actual);
        Object expectedValue = getPropertyValue(property, expected);

        if (context.isRecursive(actualValue))
            return resultList;
        context.setActualObj(actualValue);
        context.setPropertyDescriptor(property);


        resultList.addAll(comparator.areEqual(actualValue, expectedValue, context));

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
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }
}
