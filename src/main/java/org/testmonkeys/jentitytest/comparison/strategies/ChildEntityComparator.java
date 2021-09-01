package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.EntityComparator;
import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;


public class ChildEntityComparator extends AbstractComparator {

    public ChildEntityComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }

    /**
     * @param actual   actual object
     * @param expected expected object
     * @param context  context of this comparison
     * @return result set of the comparison
     * @throws JEntityTestException if comparison is impossible
     */
    @Override
    protected ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {

        EntityComparator comparator = new EntityComparator();
        return comparator.compare(actual, expected, context);
    }
}
