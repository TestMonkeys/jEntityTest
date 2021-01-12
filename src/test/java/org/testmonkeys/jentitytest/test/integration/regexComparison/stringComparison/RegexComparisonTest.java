package org.testmonkeys.jentitytest.test.integration.regexComparison.stringComparison;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.hamcrest.Entity;

public class RegexComparisonTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testPassingCase() {
        Model actual = new Model();
        actual.setField("Test");
        Model expected = new Model();
        expected.setField("T.st");
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test (expected = AssertionError.class)
    public void testFailingCase() {
        Model actual = new Model();
        actual.setField("Fest");
        Model expected = new Model();
        expected.setField("Rest|Nest");
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

}
