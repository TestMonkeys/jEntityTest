package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

public interface Comparator {

    /**
     * @param actual   actual object
     * @param expected expected object
     * @param context  context of this comparison
     * @return result set of the comparison
     * @throws JEntityTestException if comparison is impossible
     */
    ResultSet compare(Object actual, Object expected, ComparisonContext context);
}
