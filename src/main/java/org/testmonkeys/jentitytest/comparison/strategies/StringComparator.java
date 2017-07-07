package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.StringComparison;

public class StringComparator extends AbstractComparator {

    private final char[] controlChars = {'\n', '\t', '\r', '\0', '\b', '\f'};
    private final String[] escControlChars = {"\\n", "\\t", "\\r", "\\0", "\\b", "\\f"};

    private boolean caseSensitive = true;
    private boolean trim;
    private char[] ignoreCharacters = {};

    public StringComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }

    public StringComparator(StringComparison annotation) {
        registerPreConditionalCheck(new NullConditionalCheck());
        caseSensitive = annotation.caseSensitive();
        trim = annotation.trim();
        ignoreCharacters = annotation.ignoreCharacters();
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

    public ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        String actualValue;
        String expectedValue;

        try {
            actualValue = (String) actual;
            expectedValue = (String) expected;
        } catch (ClassCastException castException) {
            throw new JEntityTestException("Expected and Actual values must be of type " + String.class.getName(),
                    castException);
        }

        if (ignoreCharacters != null) {
            for (char ignore : ignoreCharacters) {
                String ignoreString = String.valueOf(ignore);
                actualValue = actualValue.replace(ignoreString, "");
                expectedValue = expectedValue.replace(ignoreString, "");
            }
        }
        if (trim) {
            actualValue = actualValue.trim();
            expectedValue = expectedValue.trim();
        }

        Status status;
        if (caseSensitive) {
            status = Status.valueOf(actualValue.equals(expectedValue));
        } else {
            status = Status.valueOf(actualValue.equalsIgnoreCase(expectedValue));
        }
        return new ResultSet().with(status, context, showControlChars(actualValue), showControlChars(expectedValue));
    }

    @Override
    protected String describe() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName())
                .append(" with parameters: case sensitive = ").append(caseSensitive)
                .append(", trim = ").append(trim)
                .append(", ignoring characters: ").append(charArrayToString(ignoreCharacters));
        return sb.toString();
    }

    private StringBuilder showControlChars(Object inputObj) {
        StringBuilder res = new StringBuilder(inputObj.toString());
        for (int i = 0; i < controlChars.length; i++) {
            int index = res.indexOf(String.valueOf(controlChars[i]));
            while (index != -1) {
                res.replace(index, index + 1, escControlChars[i]);
                index = res.indexOf(String.valueOf(controlChars[i]));
            }
        }
        return res;
    }

    private String charArrayToString(char[] array) {
        if ((array == null) || (array.length == 0))
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(showControlChars(array[i]).append(", "));
        }
        return sb.deleteCharAt(sb.lastIndexOf(",")).toString();
    }
}
