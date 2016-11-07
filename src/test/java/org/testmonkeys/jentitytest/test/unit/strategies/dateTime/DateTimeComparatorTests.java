package org.testmonkeys.jentitytest.test.unit.strategies.dateTime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.property.DateTimeComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateTimeComparatorTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    PropertyDescriptor propertyDescriptor;
    ComparisonContext context;
    SimpleModel actual;
    SimpleModel expected;

    @Before
    public void init() throws Throwable {
        context = new ComparisonContext();
        actual = new SimpleModel();
        expected = new SimpleModel();

    }

    private DateTimeComparator getComparator(int delta, ChronoUnit unit) {
        DateTimeComparator comparator = new DateTimeComparator();
        try {
            Field field = DateTimeComparator.class.getDeclaredField("delta");
            field.setAccessible(true);
            field.set(comparator, delta);
            field.setAccessible(false);
            field = DateTimeComparator.class.getDeclaredField("unit");
            field.setAccessible(true);
            field.set(comparator, unit);
            field.setAccessible(false);
        } catch (Exception e) {

        }
        return comparator;
    }

    @Test
    public void strategy_dateTimeComparator_noDelta_expectedNull() throws Throwable {
        DateTimeComparator comparator = new DateTimeComparator();
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 05, 22, 33, 01, 1));
        expected.setLocalDateTime(null);
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_noDelta_actualNull() throws Throwable {
        DateTimeComparator comparator = new DateTimeComparator();
        actual.setLocalDateTime(null);
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 05, 22, 33, 01, 1));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_noDelta_bothNull() throws Throwable {
        DateTimeComparator comparator = new DateTimeComparator();
        actual.setLocalDateTime(null);
        expected.setLocalDateTime(null);
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertTrue(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_noDelta_equal() throws Throwable {
        DateTimeComparator comparator = new DateTimeComparator();
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 05, 22, 33, 01, 1));
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 05, 22, 33, 01, 1));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertTrue(result.isPassed());

    }

    @Test
    public void strategy_dateTimeComparator_noDelta_different_nanoSeconds_expectedMore() throws Throwable {
        DateTimeComparator comparator = new DateTimeComparator();
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 1, 1));
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 1, 2));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());

    }

    @Test
    public void strategy_dateTimeComparator_noDelta_different_nanoSeconds_expectedLess() throws Throwable {
        DateTimeComparator comparator = new DateTimeComparator();
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 1, 3));
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 1, 2));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());

    }

    @Test
    public void strategy_dateTimeComparator_noDelta_different_seconds_expectedMore() throws Throwable {
        DateTimeComparator comparator = new DateTimeComparator();
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 1, 1));
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 2, 2));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());

    }

    @Test
    public void strategy_dateTimeComparator_noDelta_different_seconds_expectedLess() throws Throwable {
        DateTimeComparator comparator = new DateTimeComparator();
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 3, 1));
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 2, 1));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_3SecondDelta_inRange_seconds_expectedLess() throws Throwable {
        DateTimeComparator comparator = getComparator(3, ChronoUnit.SECONDS);
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 5, 1));
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 2, 1));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertTrue(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_3SecondDelta_inRange_seconds_actualLess() throws Throwable {
        DateTimeComparator comparator = getComparator(3, ChronoUnit.SECONDS);
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 2, 1));
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 5, 1));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertTrue(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_3SecondDelta_notInRange_seconds_expectedLess() throws Throwable {
        DateTimeComparator comparator = getComparator(3, ChronoUnit.SECONDS);
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 6, 1));
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 2, 1));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_3SecondDelta_notInRange_seconds_actualLess() throws Throwable {
        DateTimeComparator comparator = getComparator(3, ChronoUnit.SECONDS);
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 2, 1));
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 6, 1));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_edge_3SecondDelta_notInRange_seconds_actualMIN() throws Throwable {
        DateTimeComparator comparator = getComparator(3, ChronoUnit.SECONDS);
        actual.setLocalDateTime(LocalDateTime.MIN);
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 6, 1));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_edge_3SecondDelta_notInRange_seconds_expectedMIN() throws Throwable {
        DateTimeComparator comparator = getComparator(3, ChronoUnit.SECONDS);
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 6, 1));
        expected.setLocalDateTime(LocalDateTime.MIN);
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_edge_3SecondDelta_notInRange_seconds_actualMAX() throws Throwable {
        DateTimeComparator comparator = getComparator(3, ChronoUnit.SECONDS);
        actual.setLocalDateTime(LocalDateTime.MAX);
        expected.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 6, 1));
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_edge_3SecondDelta_notInRange_seconds_expectedMAX() throws Throwable {
        DateTimeComparator comparator = getComparator(3, ChronoUnit.SECONDS);
        actual.setLocalDateTime(LocalDateTime.of(2016, 10, 5, 22, 33, 6, 1));
        expected.setLocalDateTime(LocalDateTime.MAX);
        propertyDescriptor = new PropertyDescriptor("LocalDateTime", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());
    }

    @Test
    public void strategy_dateTimeComparator_err_dateField() throws Throwable {
        expectedException.expect(JEntityTestException.class);
        expectedException.expectMessage("Expected and Actual values must of type java.time.temporal.Temporal");
        DateTimeComparator comparator = getComparator(3, ChronoUnit.SECONDS);
        actual.setDate(new Date());
        expected.setDate(new Date());
        propertyDescriptor = new PropertyDescriptor("Date", SimpleModel.class);
        List<ComparisonResult> results = comparator.areEqual(propertyDescriptor, actual, expected, context);
        ComparisonResult result = results.get(0);
        assertFalse(result.isPassed());
    }
}
