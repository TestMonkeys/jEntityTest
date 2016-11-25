package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class MultiResultComparator implements Comparator {

    private List<ConditionalCheck> conditionalChecks = new ArrayList<>();

    @Override
    public void registerPreConditionalCheck(ConditionalCheck conditionalCheck) {
        conditionalChecks.add(conditionalCheck);
    }

    @Override
    public List<ComparisonResult> areEqual(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) throws JEntityTestException {
        List<ComparisonResult> resultList = new LinkedList<>();

        Object actualValue = getPropertyValue(property, actual);
        Object expectedValue = getPropertyValue(property, expected);

        if (context.isRecursive(actualValue))
            return resultList;
        context.setActualObj(actualValue);
        context.setPropertyDescriptor(property);

        List<ConditionalCheckResult> conditionalResults = runConditionals(actualValue, expectedValue, context);
        if (conditionalResults.stream().anyMatch(res -> res.stopComparison() || !res.isPassed())) {
            resultList.addAll(conditionalResults);
            return resultList;
        }

        resultList.addAll(computeComparison(actualValue, expectedValue, context));

        return resultList;
    }

    protected Object getPropertyValue(PropertyDescriptor property, Object obj) {
        try {
            return property.getReadMethod().invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new JEntityTestException("Could not read property from object", e);
        }
    }

    protected List<ConditionalCheckResult> runConditionals(Object actualValue, Object expectedValue, ComparisonContext context) throws JEntityTestException {
        List<ConditionalCheckResult> results = new ArrayList<>();
        for (int i = conditionalChecks.size() - 1; i >= 0; i--) {
            ConditionalCheck check = conditionalChecks.get(i);
            results.add(check.runCheck(actualValue, expectedValue, context));
            if (results.stream().anyMatch(res -> res.stopComparison()))
                return results;
        }
        return results;
    }

    protected abstract List<ComparisonResult> computeComparison(Object actual, Object expected, ComparisonContext context) throws JEntityTestException;
}
