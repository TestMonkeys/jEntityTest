package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.util.NullComparator;
import org.testmonkeys.jentitytest.comparison.util.NullComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

public class StringComparator extends SingleResultComparator {
    private final NullComparator nullComparatorHelper = new NullComparator();
    private boolean caseSensitive = true;
    private char[] ignoreCharacters = {};

    public ComparisonResult computeSingleComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        ComparisonResult comparisonResult = new ComparisonResult(false, context, actualValue, expectedValue);

        NullComparisonResult nullComparisonResult = nullComparatorHelper.compareOnNulls(actualValue, expectedValue,
                context);
        if (!nullComparisonResult.isPassed() || nullComparisonResult.stopComparison()) {
            return nullComparisonResult;
        }

        String actual;
        String expected;

        try {
            actual = (String) actualValue;
            expected = (String) expectedValue;
        } catch (ClassCastException castException) {
            throw new JEntityTestException("Expected and Actual values must of type " + String.class.getName(),
                    castException);
        }

        for (char ignore : ignoreCharacters) {
            String ignoreString = String.valueOf(ignore);
            actual = actual.replaceAll(ignoreString, "");
            expected = expected.replaceAll(ignoreString, "");
        }

        if (caseSensitive) {
            comparisonResult.setPassed(actual.equals(expected));
        } else {
            comparisonResult.setPassed(actual.equalsIgnoreCase(expected));
        }
        return comparisonResult;
    }
}
