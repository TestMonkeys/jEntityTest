package org.testmonkeys.jentitytest.comparison.property;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;

import java.beans.PropertyDescriptor;
import java.util.List;

public class SimpleTypeComparator implements Comparator {

    @Override
    public ComparisonResult areEqual(PropertyDescriptor property, Object actual, Object expected) {
        ComparisonResult result =new ComparisonResult();
        result.setPassed(actual.equals(expected));
        return result;
    }
}
