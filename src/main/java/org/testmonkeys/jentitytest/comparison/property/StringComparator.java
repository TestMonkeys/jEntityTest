package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

public class StringComparator extends SingleResultComparator {
    private boolean caseSensitive = true;
    private char[] ignoreCharacters = {};

    public StringComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }

    public ComparisonResult computeSingleComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        ComparisonResult comparisonResult = new ComparisonResult(false, context, actualValue, expectedValue);

        String actual;
        String expected;

        try {
            actual = (String) actualValue;
            expected = (String) expectedValue;
        } catch (ClassCastException castException) {
            throw new JEntityTestException("Expected and Actual values must of type " + String.class.getName(),
                    castException);
        }

        if (actual != null && expected != null) {
            for (char ignore : ignoreCharacters) {
                String ignoreString = String.valueOf(ignore);
                actual = actual.replaceAll(ignoreString, "");
                expected = expected.replaceAll(ignoreString, "");
            }
        }


        if (caseSensitive) {
            comparisonResult.setPassed(actual.equals(expected));
        } else {
            comparisonResult.setPassed(actual.equalsIgnoreCase(expected));
        }
        return comparisonResult;
    }
}
