package org.testmonkeys.jentitytest.test.integration.stringComparison;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.hamcrest.Entity;

public class StringComparisonTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testPassingCase() {
        Model actual = new Model();
        actual.setField("Test");
        Model expected = new Model();
        expected.setField("test");
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailingCase() {
        Model actual = new Model();
        actual.setField("Rest");
        Model expected = new Model();
        expected.setField("test");
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void testPassingSpecChars() {
        Model actual = new Model();
        actual.setField("\rtest\n");
        Model expected = new Model();
        expected.setField("test");
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailingSpecChars() {
        Model actual = new Model();
        actual.setField("\ttest\n");
        Model expected = new Model();
        expected.setField("test");
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void testPassingNulls() {
        Model actual = new Model();
        Model expected = new Model();
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailingNulls() {
        Model actual = new Model();
        actual.setField("\ttest\n");
        Model expected = new Model();
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void testFailingMessage() {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("\n\tExpected: test\n\tActual: \\ttest" +
                "\nComparison was performed using StringComparator with parameters: case sensitive = false, " +
                "trim = false, ignoring characters: \\n, \\r");
        Model actual = new Model();
        actual.setField("\ttest\r\n");
        Model expected = new Model();
        expected.setField("test");
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void testFailingMessageSecondEntryControlChar() {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("\n\tExpected: test\n\tActual: \\ttest\\t" +
                "\nComparison was performed using StringComparator with parameters: case sensitive = false, " +
                "trim = false, ignoring characters: \\n, \\r");
        Model actual = new Model();
        actual.setField("\ttest\t\r\n");
        Model expected = new Model();
        expected.setField("test");
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }
}
