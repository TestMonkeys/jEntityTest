package org.testmonkeys.jentitytest.comparison.impl;

import org.testmonkeys.jentitytest.comparison.Comparator;

/**
 * Created by cpascal on 6/7/2016.
 */
public class SimpleTypeCoparator implements Comparator {

    @Override
    public boolean areEqual(Object actual, Object expected) {
        return actual.equals(expected);
    }
}
