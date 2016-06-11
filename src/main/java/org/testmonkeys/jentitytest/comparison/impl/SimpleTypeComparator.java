package org.testmonkeys.jentitytest.comparison.impl;

import org.testmonkeys.jentitytest.comparison.Comparator;

public class SimpleTypeComparator implements Comparator {

    @Override
    public boolean areEqual(Object actual, Object expected) {
        return actual.equals(expected);
    }
}
