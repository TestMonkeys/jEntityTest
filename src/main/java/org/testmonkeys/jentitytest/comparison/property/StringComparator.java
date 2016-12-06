package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

public class StringComparator extends SingleResultComparator {

    private boolean caseSensitive = true;
    private boolean trim = false;
    private char[] ignoreCharacters = {};

    public StringComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public void setTrim(boolean trim) {
        this.trim = trim;
    }

    public void setIgnoreCharacters(char[] ignoreCharacters) {
        this.ignoreCharacters = ignoreCharacters;
    }

    public ComparisonResult computeSingleComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        ComparisonResult comparisonResult = new ComparisonResult(false, context, actualValue, expectedValue);

        String actual;
        String expected;

        try {
            actual = (String) actualValue;
            expected = (String) expectedValue;
        } catch (ClassCastException castException) {
            throw new JEntityTestException("Expected and Actual values must be of type " + String.class.getName(),
                    castException);
        }

        if (ignoreCharacters != null) {
            for (char ignore : ignoreCharacters) {
                String ignoreString = String.valueOf(ignore);
                actual = actual.replace(ignoreString, "");
                expected = expected.replace(ignoreString, "");
            }
        }
        if (trim) {
            actual = actual.trim();
            expected = expected.trim();
        }

        if (caseSensitive) {
            comparisonResult.setPassed(actual.equals(expected));
        } else {
            comparisonResult.setPassed(actual.equalsIgnoreCase(expected));
        }

        return comparisonResult;
    }
}
