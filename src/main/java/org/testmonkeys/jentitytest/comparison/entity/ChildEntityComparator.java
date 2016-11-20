package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.MultiResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.util.NullComparator;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.util.List;


public class ChildEntityComparator extends MultiResultComparator {

    public ChildEntityComparator() {
        registerPreConditionalCheck(new NullComparator());
    }

    @Override
    protected List<ComparisonResult> computeComparison(Object actualValue, Object expectedValue, ComparisonContext context) throws JEntityTestException {
        EntityComparator comparator = new EntityComparator();
        return comparator.compare(actualValue, expectedValue, context);
    }
}
