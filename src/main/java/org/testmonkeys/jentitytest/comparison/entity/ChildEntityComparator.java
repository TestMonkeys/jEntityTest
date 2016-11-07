package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.MultiResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.util.NullComparator;
import org.testmonkeys.jentitytest.comparison.util.NullComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ChildEntityComparator extends MultiResultComparator {

    private final NullComparator nullComparatorHelper = new NullComparator();
    private List<ComparisonResult> comparisonResults;

    public ChildEntityComparator() {
        comparisonResults = new LinkedList<>();
    }

    @Override
    protected List<ComparisonResult> computeComparison(PropertyDescriptor property, Object actualValue, Object expectedValue, ComparisonContext context) throws JEntityTestException {

        NullComparisonResult nullComparisonResult = nullComparatorHelper.compareOnNulls(actualValue, expectedValue, context);
        if (!nullComparisonResult.isPassed() || nullComparisonResult.stopComparison()) {
            List<ComparisonResult> comparisonResults = new ArrayList<>();
            comparisonResults.add(nullComparisonResult);
            return comparisonResults;
        }


        EntityComparator comparator = new EntityComparator();
        return comparator.compare(actualValue, expectedValue, context);

    }
}
