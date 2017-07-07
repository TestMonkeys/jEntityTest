package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.util.ArrayList;
import java.util.List;

import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;

public abstract class AbstractComparator implements Comparator {

    private final List<PreComparisonCheck> preComparisonChecks = new ArrayList<>();

    protected void registerPreConditionalCheck(PreComparisonCheck preComparisonCheck) {
        preComparisonChecks.add(preComparisonCheck);
    }

    @Override
    public ResultSet compare(Object actual, Object expected, ComparisonContext context) {
        ResultSet resultList = new ResultSet();

        List<ConditionalCheckResult> conditionalResults = runConditionals(actual, expected, context);
        if (conditionalResults.stream().anyMatch(res -> res.isComparisonFinished() || (res.getStatus() == Failed))) {
            resultList.addAll(conditionalResults);
            return resultList;
        }

        context.setComparatorDetails(describe());
        resultList.addAll(computeComparison(actual, expected, context));
        return resultList;
    }

    /**
     * @param actualValue
     * @param expectedValue
     * @param context
     * @return
     * @throws JEntityTestException
     */
    private List<ConditionalCheckResult> runConditionals(Object actualValue, Object expectedValue,
                                                         ComparisonContext context) {
        List<ConditionalCheckResult> results = new ArrayList<>();
        for (int i = preComparisonChecks.size() - 1; i >= 0; i--) {
            PreComparisonCheck check = preComparisonChecks.get(i);
            results.add(check.runCheck(actualValue, expectedValue, context));
            if (results.stream().anyMatch(ConditionalCheckResult::isComparisonFinished))
                return results;
        }
        return results;
    }

    /**
     * @param actual
     * @param expected
     * @param context
     * @return
     * @throws JEntityTestException
     */
    protected abstract ResultSet computeComparison(Object actual, Object expected, ComparisonContext context);

    protected String describe(){
        return getClass().getSimpleName();
    }

}
