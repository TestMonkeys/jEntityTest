package org.testmonkeys.jentitytest.test.stringComparison;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.Entity;

public class StringComparisonTest {

    @Test
    public void testPassingCase() {
        Model actual = new Model();
        actual.setField("Test");
        Model expected = new Model();
        expected.setField("test");
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test (expected = AssertionError.class)
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

    @Test (expected = AssertionError.class)
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

    @Test (expected = AssertionError.class)
    public void testFailingNulls() {
        Model actual = new Model();
        actual.setField("\ttest\n");
        Model expected = new Model();
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

}
