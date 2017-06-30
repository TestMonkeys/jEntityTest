package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.StringComparison;

public class StringComparator extends AbstractComparator {

    private final char[] controlChars = {'\n', '\t', '\r', '\0', '\b', '\f'};
    private final String[] escControlChars = {"\\n", "\\t", "\\r", "\\0", "\\b", "\\f"};

    private boolean caseSensitive = true;
    private boolean trim;
    private char[] ignoreCharacters = {};

    public StringComparator() {
        this.registerPreConditionalCheck(new NullConditionalCheck());
    }

    public StringComparator(StringComparison annotation) {
        super(annotation);
        this.registerPreConditionalCheck(new NullConditionalCheck());
        this.setCaseSensitive(annotation.caseSensitive());
        this.setTrim(annotation.trim());
        this.setIgnoreCharacters(annotation.ignoreCharacters());
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

        if (this.ignoreCharacters != null) {
            for (char ignore : this.ignoreCharacters) {
                String ignoreString = String.valueOf(ignore);
                actual = actual.replace(ignoreString, "");
                expected = expected.replace(ignoreString, "");
            }
        }
        if (this.trim) {
            actual = actual.trim();
            expected = expected.trim();
        }

        boolean passed;
        if (this.caseSensitive) {
            passed = actual.equals(expected);
        } else {
            passed = actual.equalsIgnoreCase(expected);
        }
        return new ResultSet().with(passed, context, this.showControlChars(actual), this.showControlChars(expected));
    }

    @Override
    protected String describe() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName())
                .append(" with parameters: case sensitive = ").append(this.caseSensitive)
                .append(", trim = ").append(this.trim)
                .append(", ignoring characters: ").append(this.charArrayToString(this.ignoreCharacters));
        return sb.toString();
    }

    private StringBuilder showControlChars(Object inputObj) {
        StringBuilder res = new StringBuilder(inputObj.toString());
        for (int i = 0; i < this.controlChars.length; i++) {
            int index = res.indexOf(String.valueOf(this.controlChars[i]));
            while (index != -1) {
                res.replace(index, index + 1, this.escControlChars[i]);
                index = res.indexOf(String.valueOf(this.controlChars[i]));
            }
        }
        return res;
    }

    private String charArrayToString(char[] array) {
        if (array == null || array.length == 0)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(this.showControlChars(array[i]).append(", "));
        }
        return sb.deleteCharAt(sb.lastIndexOf(",")).toString();
    }
}
