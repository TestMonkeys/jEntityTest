package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.SingleResultComparator;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;
import org.testmonkeys.jentitytest.utils.StringUtils;

import static org.testmonkeys.jentitytest.utils.StringUtils.charArrayToString;

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
        comparisonResult.setActual(StringUtils.showControlChars(actual)); //TODO discuss
        comparisonResult.setExpected(StringUtils.showControlChars(expected));
        return comparisonResult;
    }

    @Override
    protected String describe(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName())
                .append(" with parameters: case sensitive = ").append(caseSensitive)
                .append(", trim = ").append(trim)
                .append(", ignoring characters: ").append(charArrayToString(ignoreCharacters));
        return sb.toString();
    }
}
