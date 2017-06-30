package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

public class BadComparator implements Comparator {

    public BadComparator(int param) {
    }


    @Override
    public ResultSet compare(Object actual, Object expected, ComparisonContext context) throws JEntityTestException {
        return null;
    }
}
