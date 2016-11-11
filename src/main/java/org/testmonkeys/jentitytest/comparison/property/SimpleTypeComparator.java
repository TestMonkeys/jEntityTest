package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.util.NullComparator;
import org.testmonkeys.jentitytest.comparison.util.NullComparisonResult;

import java.beans.PropertyDescriptor;

public class SimpleTypeComparator extends SingleResultComparator {
    private final NullComparator nullComparatorHelper = new NullComparator();
    @Override
    public ComparisonResult computeSingleComparison(PropertyDescriptor property, Object actualValue, Object expectedValue, ComparisonContext context) {
        ComparisonResult result = new ComparisonResult(false, context, actualValue, expectedValue);
        NullComparisonResult nullComparisonResult = nullComparatorHelper.compareOnNulls(actualValue, expectedValue, context);
        if (!nullComparisonResult.isPassed() || nullComparisonResult.stopComparison())
            return nullComparisonResult;
        result.setPassed(actualValue.equals(expectedValue));

        return result;
    }
}
