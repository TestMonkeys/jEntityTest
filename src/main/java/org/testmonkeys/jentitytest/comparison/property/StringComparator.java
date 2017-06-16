package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.framework.JEntityTestException;
import org.testmonkeys.jentitytest.utils.StringUtils;

import static org.testmonkeys.jentitytest.utils.StringUtils.charArrayToString;

public class StringComparator extends AbstractComparator {

    private boolean caseSensitive = true;
    private boolean trim;
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

    public ResultSet computeComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
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

        boolean passed;
        if (caseSensitive) {
            passed=actual.equals(expected);
        } else {
            passed = actual.equalsIgnoreCase(expected);
        }
        return new ResultSet(passed, context, StringUtils.showControlChars(actual), StringUtils.showControlChars(expected));
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
