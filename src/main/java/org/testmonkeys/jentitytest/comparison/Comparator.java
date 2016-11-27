package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.util.List;

public interface Comparator {

    List<ComparisonResult> areEqual(Object actual, Object expected, ComparisonContext context) throws JEntityTestException;
}
