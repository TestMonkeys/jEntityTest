package org.testmonkeys.jentitytest.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.entity.EntityComparator;

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
        EntityComparator comparator = new EntityComparator();
        result.addAll(comparator.compare(actualItem,expected,comparisonModel));
        return result.stream().allMatch(p -> p.isPassed());
    }

    @Override
    public void describeTo(Description description) {

    }
}
