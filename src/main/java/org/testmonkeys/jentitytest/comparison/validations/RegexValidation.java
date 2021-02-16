package org.testmonkeys.jentitytest.comparison.validations;

import lombok.Getter;
import lombok.Setter;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.framework.ValidateRegex;

import java.util.regex.Pattern;

import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;
import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

public class RegexValidation extends AbstractValidation {

    @Getter
    @Setter
    private String regExp;
    @Getter
    @Setter
    private boolean failOnNull = true;

    public RegexValidation(ValidateRegex annotation) {
        regExp = annotation.expression();
        failOnNull = annotation.nullHandling() == ValidateRegex.NullHandling.Fail;
    }

    public RegexValidation() {
    }

    public ConditionalCheckResult runCheck(Object actual, Object expected, ComparisonContext context) {
        context.setComparatorDetails("RegexValidation");
        ConditionalCheckResult result = new ConditionalCheckResult(Passed, context, actual, expected);
        result.setExpected((failOnNull ? "not null & " : "") + "matches regexp: " + regExp);
        if (actual == null && failOnNull)
            result.setStatus(Failed);
        else if (actual != null) {
            if (!Pattern.matches(regExp, String.valueOf(actual))) {
                result.setStatus(Failed);
            }
        }
        return result;
    }
}
