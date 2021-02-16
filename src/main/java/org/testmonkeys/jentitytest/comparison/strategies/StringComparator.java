package org.testmonkeys.jentitytest.comparison.strategies;

import lombok.Getter;
import lombok.Setter;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.StringComparison;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class StringComparator extends AbstractComparator {

    private static final char NEW_LINE = '\n';
    private static final char TAB = '\t';
    private static final char CARRIAGE_RETURN = '\r';
    private static final char NULL = '\0';
    private static final char BACKSPACE = '\b';
    private static final char FORMFEED = '\f';

    @SuppressWarnings("HardCodedStringLiteral")
    private static final String ESCAPED_NEW_LINE = "\\n";
    @SuppressWarnings("HardCodedStringLiteral")
    private static final String ESCAPED_TAB = "\\t";
    @SuppressWarnings("HardCodedStringLiteral")
    private static final String ESCAPED_CARRIAGE_RETURN = "\\r";
    private static final String ESCAPED_NULL = "\\0";
    @SuppressWarnings("HardCodedStringLiteral")
    private static final String ESCAPED_BACKSPACE = "\\b";
    @SuppressWarnings("HardCodedStringLiteral")
    private static final String ESCAPED_FORMFEED = "\\f";
    private static final String EMPTY_STRING = "";

    private final char[] controlChars = {NEW_LINE, TAB, CARRIAGE_RETURN, NULL, BACKSPACE, FORMFEED};
    private final String[] escControlChars = {ESCAPED_NEW_LINE, ESCAPED_TAB, ESCAPED_CARRIAGE_RETURN, ESCAPED_NULL,
            ESCAPED_BACKSPACE, ESCAPED_FORMFEED};

    @Getter
    @Setter
    private boolean caseSensitive = true;
    @Getter
    @Setter
    private boolean trim;
    @Getter
    @Setter
    private List<String> ignoreCharacters = new ArrayList<>();

    public StringComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }

    public StringComparator(StringComparison annotation) {
        registerPreConditionalCheck(new NullConditionalCheck());
        caseSensitive = annotation.caseSensitive();
        trim = annotation.trim();
        for (char c : annotation.ignoreCharacters())
            ignoreCharacters.add(String.valueOf(c));
    }

    public ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        String actualValue;
        String expectedValue;

        try {
            actualValue = (String) actual;
            expectedValue = (String) expected;
        } catch (ClassCastException castException) {
            throw new JEntityTestException(MessageFormat.format(
                    Resources.getString(Resources.err_actual_and_expected_must_be_of_type), String.class.getName()),
                    castException);
        }

        if (ignoreCharacters != null) {
            for (String ignoreString : ignoreCharacters) {
                actualValue = actualValue.replace(ignoreString, EMPTY_STRING);
                expectedValue = expectedValue.replace(ignoreString, EMPTY_STRING);
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
        if ((ignoreCharacters != null) && (ignoreCharacters.size() > 0))
            return MessageFormat.format(Resources.getString(Resources.desc_string_ignoring_chars),
                    getClass().getSimpleName(), caseSensitive, trim,
                    String.join(", ", getIgnoredCharactersEscaped()));
        else
            return MessageFormat.format(Resources.getString(Resources.desc_string),
                    getClass().getSimpleName(), caseSensitive, trim,
                    String.join(", "));
    }

    @SuppressWarnings("MethodWithMultipleLoops")
    private String showControlChars(Object inputObj) {
        String res = inputObj.toString();
        for (int i=0; i<controlChars.length;i++){
            while (res.contains(Character.toString(controlChars[i])))
                res=res.replace(Character.toString(controlChars[i]),escControlChars[i]);
        }
        return res;
    }

    private List<String> getIgnoredCharactersEscaped() {
        List<String> escapedChars = new ArrayList<>();
        if (ignoreCharacters != null)
            for (String c : ignoreCharacters) {
                escapedChars.add(showControlChars(c));
            }
        return escapedChars;
    }


}
