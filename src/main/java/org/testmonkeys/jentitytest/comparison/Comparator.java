package org.testmonkeys.jentitytest.comparison;

import java.beans.PropertyDescriptor;
import java.util.List;

public interface Comparator {

    ComparisonResult areEqual(PropertyDescriptor property, Object actual, Object expected);


}
