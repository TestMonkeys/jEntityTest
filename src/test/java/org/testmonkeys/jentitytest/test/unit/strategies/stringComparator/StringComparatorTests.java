package org.testmonkeys.jentitytest.test.unit.strategies.stringComparator;

import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.property.StringComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringComparatorTests {

    Model actual = new Model();
    Model expected = new Model();
    ComparisonContext context = new ComparisonContext();
    char[] ignore = {};

    private StringComparator getComparator(boolean caseSensitive, boolean ignoreTrailing,
                                           char[] ignoreChars) {
        StringComparator comparator = new StringComparator();
        try {
            Field field = StringComparator.class.getDeclaredField("caseSensitive");
            field.setAccessible(true);
            field.set(comparator, caseSensitive);
            field.setAccessible(false);
            field = StringComparator.class.getDeclaredField("ignoreCharacters");
            field.setAccessible(true);
            field.set(comparator, ignoreChars);
            field.setAccessible(false);
            field = StringComparator.class.getDeclaredField("ignoreTrailing");
            field.setAccessible(true);
            field.set(comparator, ignoreTrailing);
            field.setAccessible(false);
        } catch (Exception e) {

        }
        return comparator;
    }


    @Test
    public void stringComparatorPassNulls() throws IntrospectionException {
        actual.setField(null);
        expected.setField(null);

        StringComparator comparator = getComparator(true, false, null);
        PropertyDescriptor descriptor = new PropertyDescriptor("field", Model.class);
        List<ComparisonResult> resultList = comparator.areEqual(descriptor, actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test(expected = AssertionError.class)
    public void stringComparatorFailNull() throws IntrospectionException {
        actual.setField(null);
        expected.setField("test");

        StringComparator comparator = getComparator(false, false, null);
        PropertyDescriptor descriptor = new PropertyDescriptor("field", Model.class);
        List<ComparisonResult> resultList = comparator.areEqual(descriptor, actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test
    public void stringComparatorPassCaseSensitive() throws IntrospectionException {
        actual.setField("test");
        expected.setField("test");

        StringComparator comparator = getComparator(true, false, ignore);
        PropertyDescriptor descriptor = new PropertyDescriptor("field", Model.class);
        List<ComparisonResult> resultList = comparator.areEqual(descriptor, actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test (expected = AssertionError.class)
    public void stringComparatorFailCaseSensitive() throws IntrospectionException {
        actual.setField("Test");
        expected.setField("test");

        StringComparator comparator = getComparator(true, false, ignore);
        PropertyDescriptor descriptor = new PropertyDescriptor("field", Model.class);
        List<ComparisonResult> resultList = comparator.areEqual(descriptor, actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test
    public void stringComparatorPassCaseInsensitive() throws IntrospectionException {
        actual.setField("Test");
        expected.setField("test");

        StringComparator comparator = getComparator(false, false, ignore);
        PropertyDescriptor descriptor = new PropertyDescriptor("field", Model.class);
        List<ComparisonResult> resultList = comparator.areEqual(descriptor, actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }


    @Test
    public void stringComparatorPassIgnoreSpecChars() throws IntrospectionException {
        actual.setField("$tes t");
        expected.setField("test");
        char[] ignore = {'$', ' '};

        StringComparator comparator = getComparator(false, false, ignore);
        PropertyDescriptor descriptor = new PropertyDescriptor("field", Model.class);
        List<ComparisonResult> resultList = comparator.areEqual(descriptor, actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test (expected = AssertionError.class)
    public void stringComparatorFailIgnoreSpecChars() throws IntrospectionException {
        actual.setField("test\t$%");
        expected.setField("test");
        char[] ignore = {'\t', '$'};

        StringComparator comparator = getComparator(false, false, ignore);
        PropertyDescriptor descriptor = new PropertyDescriptor("field", Model.class);
        List<ComparisonResult> resultList = comparator.areEqual(descriptor, actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test(expected = JEntityTestException.class)
    public void stringComparatorFailClassCast() throws IntrospectionException {
        actual.setIntField(1);
        expected.setIntField(1);

        StringComparator comparator = getComparator(false, false, ignore);
        PropertyDescriptor descriptor = new PropertyDescriptor("intField", Model.class);
        List<ComparisonResult> results = comparator.areEqual(descriptor, actual, expected, context);
    }

    @Test
    public void stringComparatorPassIgnoreTrailing() throws IntrospectionException {
        actual.setField("\t\ntest\r");
        expected.setField(" test \r\n");

        StringComparator comparator = getComparator(false, true, ignore);
        PropertyDescriptor descriptor = new PropertyDescriptor("field", Model.class);
        List<ComparisonResult> resultList = comparator.areEqual(descriptor, actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test(expected = AssertionError.class)
    public void stringComparatorFailIgnoreTrailing() throws IntrospectionException {
        actual.setField("\t\nte st\r");
        expected.setField(" test \r\n");

        StringComparator comparator = getComparator(false, true, ignore);
        PropertyDescriptor descriptor = new PropertyDescriptor("field", Model.class);
        List<ComparisonResult> resultList = comparator.areEqual(descriptor, actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }
}
