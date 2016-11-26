package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.MultiResultComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.util.Collection;
import java.util.List;

public class ChildEntityListComparator extends MultiResultComparator {


    @Override
    protected List<ComparisonResult> computeComparison(Object actual, Object expected, ComparisonContext context) throws JEntityTestException {

        Collection<?> listActual;
        Collection<?> listExpected;
        try {
            listActual = (Collection<?>) actual;
            listExpected = (Collection<?>) expected;
        } catch (ClassCastException ex) {
            throw new JEntityTestException("Actual and expected should be Generic Collections");
        }


        return null;
    }
}
