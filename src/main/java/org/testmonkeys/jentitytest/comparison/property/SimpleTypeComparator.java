package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;

import java.beans.PropertyDescriptor;
import java.util.List;

public class SimpleTypeComparator extends SingleResultComparator {

    @Override
    public ComparisonResult computeComparison(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) {

        ComparisonResult result =new ComparisonResult();
        Object actualValue=getPropertyValue(property,actual);
        Object expectedValue = getPropertyValue(property,expected);
        result.setPassed(actualValue.equals(expectedValue));
        result.setComparisonContext(context);
        result.setActual(actualValue);
        result.setExpected(expectedValue);
        return result;
    }
}
