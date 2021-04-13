package org.testmonkeys.jentitytest.test.unit.strategies.stringComparator;

import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.strategies.StringComparator;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

public class StringComparatorTests {

    private final ComparisonContext context = new ComparisonContext();

    private StringComparator getComparator(boolean caseSensitive, boolean trim) {
        return getComparator(caseSensitive, trim, null);
    }

    private StringComparator getComparator(boolean caseSensitive, boolean trim,
                                           List<String> ignoreChars) {
        StringComparator comparator = new StringComparator();
        comparator.setCaseSensitive(caseSensitive);
        comparator.setIgnoreCharacters(ignoreChars);
        comparator.setTrim(trim);
        return comparator;
    }


    @Test
    public void stringComparatorPassNulls() throws IntrospectionException {
        StringComparator comparator = getComparator(true, false, null);

        List<ComparisonResult> resultList = comparator.compare(null, null, context);
        assertTrue(resultList.get(0).getStatus() == Passed);
    }

    @Test(expected = AssertionError.class)
    public void stringComparatorFailNull() throws IntrospectionException {
        String expected = "test";
        StringComparator comparator = getComparator(false, false, null);

        List<ComparisonResult> resultList = comparator.compare(null, expected, context);
        assertTrue(resultList.get(0).getStatus() == Passed);
    }

    @Test
    public void stringComparatorPassCaseSensitive() throws IntrospectionException {
        String actual = "test";
        String expected = "test";

        StringComparator comparator = getComparator(true, false);

        List<ComparisonResult> resultList = comparator.compare(actual, expected, context);
        assertTrue(resultList.get(0).getStatus() == Passed);
    }

    @Test(expected = AssertionError.class)
    public void stringComparatorFailCaseSensitive() throws IntrospectionException {
        String actual = "Test";
        String expected = "test";


        StringComparator comparator = getComparator(true, false);

        List<ComparisonResult> resultList = comparator.compare(actual, expected, context);
        assertTrue(resultList.get(0).getStatus() == Passed);
    }

    @Test
    public void stringComparatorPassCaseInsensitive() throws IntrospectionException {
        String actual = "Test";
        String expected = "test";

        StringComparator comparator = getComparator(false, false);

        List<ComparisonResult> resultList = comparator.compare(actual, expected, context);
        assertTrue(resultList.get(0).getStatus() == Passed);
    }


    @Test
    public void stringComparatorPassIgnoreSpecChars1() throws IntrospectionException {
        String actual = "$Ates tA";
        String expected = "test";

        List<String> ignore = Arrays.asList("$", " ", "A");

        StringComparator comparator = getComparator(false, false, ignore);

        List<ComparisonResult> resultList = comparator.compare(actual, expected, context);
        assertTrue(resultList.get(0).getStatus() == Passed);
    }

    @Test
    public void stringComparatorPassIgnoreSpecChars2() throws IntrospectionException {
        String actual = "^tesAt$A";
        String expected = "test";
        List<String> ignore = Arrays.asList("^", "$", "A");

        StringComparator comparator = getComparator(false, false, ignore);

        List<ComparisonResult> resultList = comparator.compare(actual, expected, context);
        assertTrue(resultList.get(0).getStatus() == Passed);
    }

    @Test(expected = AssertionError.class)
    public void stringComparatorFailIgnoreSpecChars() throws IntrospectionException {
        String actual = "test\t$%";
        String expected = "test";
        List<String> ignore = Arrays.asList("\t", "$");

        StringComparator comparator = getComparator(false, false, ignore);

        List<ComparisonResult> resultList = comparator.compare(actual, expected, context);
        assertTrue(resultList.get(0).getStatus() == Passed);
    }

    @Test(expected = JEntityTestException.class)
    public void stringComparatorFailClassCast() throws IntrospectionException {
        int actual = 1;
        int expected = 1;

        StringComparator comparator = getComparator(false, false);

        List<ComparisonResult> results = comparator.compare(actual, expected, context);
    }

    @Test
    public void stringComparatorPassIgnoreTrailing() throws IntrospectionException {
        String actual = "\t\ntest\r";
        String expected = " test \r\n";
        List<String> empty = new ArrayList<>();
        StringComparator comparator = getComparator(false, true, empty);

        List<ComparisonResult> resultList = comparator.compare(actual, expected, context);
        assertTrue(resultList.get(0).getStatus() == Passed);
    }

    @Test(expected = AssertionError.class)
    public void stringComparatorFailIgnoreTrailing() throws IntrospectionException {
        String actual = "\t\nte st\r";
        String expected = " test \r\n";


        StringComparator comparator = getComparator(false, true);

        List<ComparisonResult> resultList = comparator.compare(actual, expected, context);
        assertTrue(resultList.get(0).getStatus() == Passed);
    }
}
