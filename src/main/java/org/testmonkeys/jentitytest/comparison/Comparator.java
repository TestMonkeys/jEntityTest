package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.util.List;

public interface Comparator {

    List<ComparisonResult> areEqual(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) throws JEntityTestException;
}
