package org.testmonkeys.jentitytest.comparison.impl;

import org.testmonkeys.jentitytest.comparison.Comparator;

/**
 * Created by cpascal on 6/11/2016.
 */
public class IgnoreComparator implements Comparator {
    @Override
    public boolean areEqual(Object actual, Object expected) {
        return true;
    }
}
