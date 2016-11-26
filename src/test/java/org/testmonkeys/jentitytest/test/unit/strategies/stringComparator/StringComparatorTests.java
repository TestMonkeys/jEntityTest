package org.testmonkeys.jentitytest.test.unit.strategies.stringComparator;

import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.property.StringComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class StringComparatorTests {

    ComparisonContext context = new ComparisonContext();

    private StringComparator getComparator(boolean caseSensitive, boolean trim) {
        return getComparator(caseSensitive, trim, null);
    }

    private StringComparator getComparator(boolean caseSensitive, boolean trim,
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
            field = StringComparator.class.getDeclaredField("trim");
            field.setAccessible(true);
            field.set(comparator, trim);
            field.setAccessible(false);
        } catch (Exception e) {

        }
        return comparator;
    }


    @Test
    public void stringComparatorPassNulls() throws IntrospectionException {
        String actual = null;
        String expected = null;

        StringComparator comparator = getComparator(true, false, null);

        List<ComparisonResult> resultList = comparator.areEqual(actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test(expected = AssertionError.class)
    public void stringComparatorFailNull() throws IntrospectionException {
        String actual = null;
        String expected = "test";

        StringComparator comparator = getComparator(false, false, null);

        List<ComparisonResult> resultList = comparator.areEqual(actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test
    public void stringComparatorPassCaseSensitive() throws IntrospectionException {
        String actual = "test";
        String expected = "test";

        StringComparator comparator = getComparator(true, false);

        List<ComparisonResult> resultList = comparator.areEqual(actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test (expected = AssertionError.class)
    public void stringComparatorFailCaseSensitive() throws IntrospectionException {
        String actual = "Test";
        String expected = "test";


        StringComparator comparator = getComparator(true, false);

        List<ComparisonResult> resultList = comparator.areEqual(actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test
    public void stringComparatorPassCaseInsensitive() throws IntrospectionException {
        String actual = "Test";
        String expected = "test";

        StringComparator comparator = getComparator(false, false);

        List<ComparisonResult> resultList = comparator.areEqual(actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }


    @Test
    public void stringComparatorPassIgnoreSpecChars1() throws IntrospectionException {
        String actual = "$Ates tA";
        String expected = "test";

        char[] ignore = {'$', ' ', 'A'};

        StringComparator comparator = getComparator(false, false, ignore);

        List<ComparisonResult> resultList = comparator.areEqual(actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test
    public void stringComparatorPassIgnoreSpecChars2() throws IntrospectionException {
        String actual = "^tesAt$A";
        String expected = "test";
        char[] ignore = {'^', '$', 'A'};

        StringComparator comparator = getComparator(false, false, ignore);

        List<ComparisonResult> resultList = comparator.areEqual(actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test (expected = AssertionError.class)
    public void stringComparatorFailIgnoreSpecChars() throws IntrospectionException {
        String actual = "test\t$%";
        String expected = "test";
        char[] ignore = {'\t', '$'};

        StringComparator comparator = getComparator(false, false, ignore);

        List<ComparisonResult> resultList = comparator.areEqual(actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test(expected = JEntityTestException.class)
    public void stringComparatorFailClassCast() throws IntrospectionException {
        int actual = 1;
        int expected = 1;

        StringComparator comparator = getComparator(false, false);

        List<ComparisonResult> results = comparator.areEqual(actual, expected, context);
    }

    @Test
    public void stringComparatorPassIgnoreTrailing() throws IntrospectionException {
        String actual = "\t\ntest\r";
        String expected = " test \r\n";

        StringComparator comparator = getComparator(false, true);

        List<ComparisonResult> resultList = comparator.areEqual(actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }

    @Test(expected = AssertionError.class)
    public void stringComparatorFailIgnoreTrailing() throws IntrospectionException {
        String actual = "\t\nte st\r";
        String expected = " test \r\n";


        StringComparator comparator = getComparator(false, true);

        List<ComparisonResult> resultList = comparator.areEqual(actual, expected, context);
        assertTrue(resultList.get(0).isPassed());
    }
}
