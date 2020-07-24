package org.testmonkeys.jentitytest.test.integration.validations.validateRegexp;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;

public class ValidateRegexpTest {

    @Test
    public void testPassing() {

        SimpleModelWithRegexp expected = new SimpleModelWithRegexp("John", 26);
        SimpleModelWithRegexp actual = new SimpleModelWithRegexp("John", 27);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }
    @Test
    public void testPassing1() {

        SimpleModelWithRegexpPassOnNull expected = new SimpleModelWithRegexpPassOnNull(null, 26);
        SimpleModelWithRegexpPassOnNull actual = new SimpleModelWithRegexpPassOnNull(null, 27);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailing0() {

        SimpleModelWithRegexp expected = new SimpleModelWithRegexp("Jonh", 26);
        SimpleModelWithRegexp actual = new SimpleModelWithRegexp("Jonh", 27);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailing() {

        SimpleModelWithRegexp expected = new SimpleModelWithRegexp(null, 26);
        SimpleModelWithRegexp actual = new SimpleModelWithRegexp(null, 26);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailing2() {
        SimpleModelWithRegexp expected = new SimpleModelWithRegexp("Johan", 26);
        SimpleModelWithRegexp actual = new SimpleModelWithRegexp(null, 26);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }
}
