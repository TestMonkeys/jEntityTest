package org.testmonkeys.jentitytest.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class EntityMatcher<T> extends BaseMatcher<T> {

    private final Object expected;
    private final EntityComparisonDictionary comparisonDictionary = EntityComparisonDictionary.getInstance();

    public EntityMatcher(Object expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actualItem) {
        ComparisonModel comparisonModel = comparisonDictionary.getComparisonModel(expected.getClass());
        List<ComparisonResult> result=new LinkedList<>();
        for (PropertyDescriptor propertyDescriptor : comparisonModel.getComparableProperties()) {
            Comparator comparator = comparisonModel.getComparator(propertyDescriptor);

            try {
                Object expectedValue = propertyDescriptor.getReadMethod().invoke(expected);
                Object actualValue = propertyDescriptor.getReadMethod().invoke(actualItem);
                result.add(comparator.areEqual(propertyDescriptor,actualValue, expectedValue));

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return result.stream().allMatch(p -> p.isPassed());
    }

    @Override
    public void describeTo(Description description) {

    }
}
