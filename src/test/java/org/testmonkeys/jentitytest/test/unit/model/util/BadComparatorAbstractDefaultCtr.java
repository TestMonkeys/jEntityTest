package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;

/**
 * Created by cpascal on 6/21/2017.
 */
public abstract class BadComparatorAbstractDefaultCtr extends AbstractComparator {

    public BadComparatorAbstractDefaultCtr() {

    }

    @Override
    protected ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        return null;
    }
}
