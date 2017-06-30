package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.EntityComparator;
import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;


public class ChildEntityComparator extends AbstractComparator {

    public ChildEntityComparator() {
        this.registerPreConditionalCheck(new NullConditionalCheck());
    }

    /**
     * @param actualValue
     * @param expectedValue
     * @param context
     * @return
     * @throws JEntityTestException
     */
    @Override
    protected ResultSet computeComparison(Object actualValue, Object expectedValue, ComparisonContext context) {

        EntityComparator comparator = new EntityComparator();
        return comparator.compare(actualValue, expectedValue, context);
    }
}
