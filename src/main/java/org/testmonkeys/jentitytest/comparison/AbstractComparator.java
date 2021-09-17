package org.testmonkeys.jentitytest.comparison;

import lombok.extern.slf4j.Slf4j;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.util.ArrayList;
import java.util.List;

import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;

@Slf4j
public abstract class AbstractComparator implements Comparator {

    private final List<PreComparisonCheck> preComparisonChecks = new ArrayList<>();

    protected void registerPreConditionalCheck(PreComparisonCheck preComparisonCheck) {
        log.trace("registering pre conditional check {}", preComparisonCheck.getClass()); //log
        preComparisonChecks.add(preComparisonCheck);
    }

    @Override
    public ResultSet compare(Object actual, Object expected, ComparisonContext context) {
        ResultSet resultList = new ResultSet();
        context.setComparatorDetails(describe());

        List<ConditionalCheckResult> conditionalResults = runConditionals(actual, expected, context);
        if (conditionalResults.stream().anyMatch(res -> res.isComparisonFinished() || (res.getStatus() == Failed))) {
            resultList.addAll(conditionalResults);
            return resultList;
        }

        resultList.addAll(computeComparison(actual, expected, context));
        return resultList;
    }

    /**
     * @param actualValue   actual object
     * @param expectedValue expected object
     * @param context       context of this comparison
     * @return result set of the comparison
     * @throws JEntityTestException if comparison is impossible
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
     * @param actual actual object
     * @param expected expected object
     * @param context context of this comparison
     * @return result set of the comparison
     * @throws JEntityTestException if comparison is impossible
     */
    protected abstract ResultSet computeComparison(Object actual, Object expected, ComparisonContext context);

    protected String describe() {
        return getClass().getSimpleName();
    }

}
