package org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.util.List;

public class BadComparator implements Comparator {

    public BadComparator(int param) {
        super();
    }


    @Override
    public List<ComparisonResult> areEqual(Object actual, Object expected, ComparisonContext context) throws JEntityTestException {
        return null;
    }
}
