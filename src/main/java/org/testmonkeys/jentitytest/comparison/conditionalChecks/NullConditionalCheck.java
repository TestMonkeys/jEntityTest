package org.testmonkeys.jentitytest.comparison.conditionalChecks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.PreComparisonCheck;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;

import java.text.MessageFormat;

import static org.testmonkeys.jentitytest.Resources.NOT_NULL_with_object;
import static org.testmonkeys.jentitytest.Resources.NULL;
import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;
import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

@SuppressWarnings("VariableNotUsedInsideIf")
public class NullConditionalCheck implements PreComparisonCheck {

    public ConditionalCheckResult runCheck(Object actual, Object expected, ComparisonContext context) {
        ConditionalCheckResult result = new ConditionalCheckResult(Passed, context, actual, expected);
        if ((actual == null) && (expected == null)) {
            result.stopComparison();
            return result;
        }

        if ((actual != null) && (expected != null))
            return result;

        result.setStatus(Failed);

        if (expected != null)
            result.setExpected(MessageFormat.format(Resources.getString(NOT_NULL_with_object), getObjectRepresentation(expected)));
        else
            result.setExpected(Resources.getString(NULL));
        if (actual != null)
            result.setActual(MessageFormat.format(Resources.getString(NOT_NULL_with_object), getObjectRepresentation(actual)));
        else
            result.setActual(Resources.getString(NULL));
        return result;
    }

    private String getObjectRepresentation(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return o.toString();
        }
    }

}
