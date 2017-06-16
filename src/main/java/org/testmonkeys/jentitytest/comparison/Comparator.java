package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.util.List;

public interface Comparator {

    /**
     * @param actual
     * @param expected
     * @param context
     * @return
     * @throws JEntityTestException
     */
    ResultSet compare(Object actual, Object expected, ComparisonContext context);
}
