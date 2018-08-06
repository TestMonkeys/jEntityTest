package org.testmonkeys.jentitytest.test.integration.dateTimeComparison;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeComparisonTests {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFailing() {
        expectedException.expect(AssertionError.class);
        Model expected = new Model();
        expected.setDateTimeField(LocalDateTime.now());

        Model actual = new Model();
        actual.setDateTimeField(LocalDateTime.now().minusMinutes(10));

        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void testFailingMessage() {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Property: Model.dateField\n\tExpected: 2016-12-10\n" +
                "\tActual: 2016-12-12\nComparison was performed using DateTimeComparator with precision up to Days");
        Model expected = new Model();
        expected.setDateField(LocalDate.of(2016, 12, 10));

        Model actual = new Model();
        actual.setDateField(LocalDate.of(2016, 12, 12));

        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void testFailingMessageDelta() {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Property: Model.timeField\n\tExpected: 11:45:22\n" +
                "\tActual: 11:45:33\nComparison was performed using DateTimeComparator with delta 10 Seconds");
        Model expected = new Model();
        expected.setTimeField(LocalTime.of(11, 45, 22));

        Model actual = new Model();
        actual.setTimeField(LocalTime.of(11, 45, 33));

        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }
}
