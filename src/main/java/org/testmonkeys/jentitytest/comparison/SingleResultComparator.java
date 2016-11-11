package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

import java.util.ArrayList;
import java.util.List;

public abstract class SingleResultComparator extends MultiResultComparator {

    @Override
    protected List<ComparisonResult> computeComparison(Object actualValue, Object expectedValue, ComparisonContext context) {
        List<ComparisonResult> result = new ArrayList<>();
        result.add(computeSingleComparison(actualValue, expectedValue, context));
        return result;
    }


    protected abstract ComparisonResult computeSingleComparison(Object actualValue, Object expectedValue, ComparisonContext context);
}
