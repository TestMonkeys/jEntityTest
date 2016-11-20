package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.util.List;

public interface Comparator {

    void registerPreConditionalCheck(ConditionalCheck conditionalCheck);

    List<ComparisonResult> areEqual(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) throws JEntityTestException;
}
