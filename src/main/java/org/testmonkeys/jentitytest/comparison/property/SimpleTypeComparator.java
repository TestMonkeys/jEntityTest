package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

import java.beans.PropertyDescriptor;

public class SimpleTypeComparator extends SingleResultComparator {

    @Override
    public ComparisonResult computeSingleComparison(PropertyDescriptor property, Object actualValue, Object expectedValue, ComparisonContext context) {
        ComparisonResult result = new ComparisonResult(false, context, actualValue, expectedValue);
        result.setPassed(actualValue.equals(expectedValue));

        return result;
    }
}
