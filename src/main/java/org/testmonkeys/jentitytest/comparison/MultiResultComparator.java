package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public abstract class MultiResultComparator implements Comparator {

    @Override
    public List<ComparisonResult> areEqual(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) throws JEntityTestException {
        List<ComparisonResult> resultList = new LinkedList<>();

        Object actualValue = this.getPropertyValue(property, actual);
        Object expectedValue = this.getPropertyValue(property, expected);

        if (context.isRecursive(actualValue))
            return resultList;
        context.setActualObj(actualValue);

        resultList.addAll(this.computeComparison(actualValue, expectedValue, context));

        return resultList;
    }

    protected Object getPropertyValue(PropertyDescriptor property, Object obj) {
        try {
            return property.getReadMethod().invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new JEntityTestException("Could not read property from object", e);
        }
    }

    protected abstract List<ComparisonResult> computeComparison(Object actual, Object expected, ComparisonContext context) throws JEntityTestException;
}
