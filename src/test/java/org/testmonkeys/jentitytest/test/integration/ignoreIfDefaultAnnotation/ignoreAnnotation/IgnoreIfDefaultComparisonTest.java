package org.testmonkeys.jentitytest.test.integration.ignoreIfDefaultAnnotation.ignoreAnnotation;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;

public class IgnoreIfDefaultComparisonTest {

    @Test
    public void testPassing() {

        SimpleModelWithIgnore expected = new SimpleModelWithIgnore(null, 26);
        SimpleModelWithIgnore actual = new SimpleModelWithIgnore("John", 26);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailing() {

        SimpleModelWithIgnore expected = new SimpleModelWithIgnore("Johan", 26);
        SimpleModelWithIgnore actual = new SimpleModelWithIgnore("John", 26);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }
}
