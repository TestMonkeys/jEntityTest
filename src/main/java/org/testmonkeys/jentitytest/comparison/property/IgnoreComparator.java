package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;

import java.beans.PropertyDescriptor;

public class IgnoreComparator implements Comparator {

    @Override
    public ComparisonResult areEqual(PropertyDescriptor property, Object actual, Object expected) {
        ComparisonResult result =new ComparisonResult();
        result.setPassed(true);
        return result;
    }
}
