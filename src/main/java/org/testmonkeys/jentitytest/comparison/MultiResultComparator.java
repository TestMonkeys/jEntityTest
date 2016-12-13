package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiResultComparator implements Comparator {

    private final List<ConditionalCheck> conditionalChecks = new ArrayList<>();

    protected void registerPreConditionalCheck(ConditionalCheck conditionalCheck) {
        conditionalChecks.add(conditionalCheck);
    }

    @Override
    public List<ComparisonResult> areEqual(Object actualValue, Object expectedValue, ComparisonContext context) {
        List<ComparisonResult> resultList = new ArrayList<>();

        List<ConditionalCheckResult> conditionalResults = runConditionals(actualValue, expectedValue, context);
        if (conditionalResults.stream().anyMatch(res -> res.stopComparison() || !res.isPassed())) {
            resultList.addAll(conditionalResults);
            return resultList;
        }

        context.setComparatorDetails(describe());
        resultList.addAll(computeComparison(actualValue, expectedValue, context));
        return resultList;
    }

    private List<ConditionalCheckResult> runConditionals(Object actualValue, Object expectedValue, ComparisonContext context) throws JEntityTestException {
        List<ConditionalCheckResult> results = new ArrayList<>();
        for (int i = conditionalChecks.size() - 1; i >= 0; i--) {
            ConditionalCheck check = conditionalChecks.get(i);
            results.add(check.runCheck(actualValue, expectedValue, context));
            if (results.stream().anyMatch(ConditionalCheckResult::stopComparison))
                return results;
        }
        return results;
    }

    protected abstract List<ComparisonResult> computeComparison(Object actual, Object expected, ComparisonContext context) throws JEntityTestException;
    protected String describe(){
        return this.getClass().getSimpleName();
    }
}
