package org.testmonkeys.jentitytest.test.integration.nullChecks;

import org.hamcrest.core.StringContains;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import static org.junit.Assert.assertThat;

public class NullCheckDetailsTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void expectedNotNull_String() {

        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Property: Address.street\n" +
                "\tExpected: not null (\"street1\")\n" +
                "\tActual: null"));

        Address expected = new Address();
        expected.setStreet("street1");
        Address actual = new Address();
        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void expectedNull_String() {

        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Property: Address.street\n" +
                "\tExpected: null\n" +
                "\tActual: not null (\"street1\")"));

        Address actual = new Address();
        actual.setStreet("street1");
        Address expected = new Address();
        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void expectedNotNull_Object() {

        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Property: NullCheckModel.address\n" +
                "\tExpected: not null ({\"street\":\"street1\"})\n" +
                "\tActual: null"));

        Address address = new Address();
        address.setStreet("street1");
        NullCheckModel expected = new NullCheckModel();
        expected.setAddress(address);
        NullCheckModel actual = new NullCheckModel();
        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void actualNotNull_Object() {

        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Property: NullCheckModel.address\n" +
                "\tExpected: null\n" +
                "\tActual: not null ({\"street\":\"street1\"})\n" +
                "Comparison was performed using SimpleTypeComparator"));

        Address address = new Address();
        address.setStreet("street1");
        NullCheckModel expected = new NullCheckModel();
        NullCheckModel actual = new NullCheckModel();
        actual.setAddress(address);
        assertThat(actual, Entity.isEqualTo(expected));
    }

}
