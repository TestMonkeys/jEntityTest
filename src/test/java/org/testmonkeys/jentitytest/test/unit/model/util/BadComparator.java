package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;

public class BadComparator implements Comparator {

    public BadComparator(int param) {
    }


    @Override
    public ResultSet compare(Object actual, Object expected, ComparisonContext context) {
        return null;
    }
}
