package org.testmonkeys.jentitytest.test.integration.noAnnotations;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;
import org.testmonkeys.jentitytest.hamcrest.matchers.EntityMatcher;

public class TestClass {

    @Test
    public void testPassing() {

        SimpleModel expected = new SimpleModel("John", 26);
        SimpleModel actual = new SimpleModel("John", 26);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailing() {

        SimpleModel expected = new SimpleModel("Johan", 26);
        SimpleModel actual = new SimpleModel("John", 26);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void debug() {
        SimpleModel expected = new SimpleModel("Johan", 26);
        SimpleModel actual = new SimpleModel("John", 26);
        EntityMatcher matcher = new EntityMatcher(expected);
        matcher.matches(actual);
    }
}
