package org.testmonkeys.jentitytest.comparison.validations;

import com.sun.net.httpserver.Authenticator;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.StringComparison;
import org.testmonkeys.jentitytest.framework.ValidateRegex;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;
import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

public class RegexValidation extends AbstractValidation {

    private final String regExp;
    private final ValidateRegex.NullHandling nullHandling;

    public RegexValidation(ValidateRegex annotation) {
        regExp = annotation.expression();
        nullHandling = annotation.nullHandling();
    }

    public ConditionalCheckResult runCheck(Object actual, Object expected, ComparisonContext context) {
        context.setComparatorDetails("RegexValidation with regular expression: "+ regExp);
        ConditionalCheckResult result = new ConditionalCheckResult(Passed, context, actual, expected);

        if (nullHandling != ValidateRegex.NullHandling.Pass || actual != null) {
            if (!Pattern.matches(regExp, String.valueOf(actual))) {
                result.setStatus(Failed);
            }
        }
        return result;
    }
}
