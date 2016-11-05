package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.MultiResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.FailedComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ChildEntityComparator extends MultiResultComparator {

    private List<ComparisonResult> comparisonResults;

    public ChildEntityComparator() {
        comparisonResults = new LinkedList<>();
    }

    @Override
    protected List<ComparisonResult> computeComparison(PropertyDescriptor property, Object actualValue, Object expectedValue, ComparisonContext context) throws JEntityTestException {

        if (actualValue == null && expectedValue == null)
            return new ArrayList<>();
        if (actualValue != null && expectedValue == null || actualValue == null) {
            List<ComparisonResult> results = new ArrayList<>();
            ComparisonResult result = new FailedComparisonResult(context,
                    actualValue == null ? "null" : "not null",
                    expectedValue == null ? "null" : "not null");
            results.add(result);
            return results;
        }


        EntityComparator comparator = new EntityComparator();
        return comparator.compare(actualValue, expectedValue, context);

    }
}
