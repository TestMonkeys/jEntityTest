package org.testmonkeys.jentitytest.test.integration.parallel;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;

/**
 * Created by cpascal on 7/18/2017.
 */
public class ParallelizableTest2 {


    @Ignore
    @Test
    public void test1() {
        Parent expected = new Parent();
        expected.setAge(23);
        expected.setFirstName("test3First");
        expected.setLastName("test3Last");

        Child expectedChild = new Child();
        expectedChild.setAge(3);
        expectedChild.setFirstName("test3Child");
        expectedChild.setLastName("test3ParentName");
        expected.setChild1(expectedChild);

        Parent actual = new Parent();
        actual.setAge(23);
        actual.setFirstName("test3First");
        actual.setLastName("test3Last");

        Child actualChild = new Child();
        actualChild.setAge(3);
        actualChild.setFirstName("test3Child");
        actualChild.setLastName("test3ParentName");
        actual.setChild1(actualChild);

        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Ignore
    @Test
    public void test2() {
        Parent expected = new Parent();
        expected.setAge(21);
        expected.setFirstName("test2First");
        expected.setLastName("test2Last");

        Child expectedChild = new Child();
        expectedChild.setAge(2);
        expectedChild.setFirstName("test2Child");
        expectedChild.setLastName("test2ParentName");
        expected.setChild1(expectedChild);

        Parent actual = new Parent();
        actual.setAge(21);
        actual.setFirstName("test2First");
        actual.setLastName("test2Last");

        Child actualChild = new Child();
        actualChild.setAge(2);
        actualChild.setFirstName("test2Child");
        actualChild.setLastName("test2ParentName");
        actual.setChild1(actualChild);

        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Ignore
    @Test
    public void test3() {
        for (int i = 0; i < 1000000; i++) {
            Parent expected = new Parent();
            expected.setAge(20);
            expected.setFirstName("test1First");
            expected.setLastName("test1Last");

            Child expectedChild = new Child();
            expectedChild.setAge(1);
            expectedChild.setFirstName("test1Child");
            expectedChild.setLastName("test1ParentName");
            expected.setChild1(expectedChild);

            Parent actual = new Parent();
            actual.setAge(20);
            actual.setFirstName("test1First");
            actual.setLastName("test1Last");

            Child actualChild = new Child();
            actualChild.setAge(1);
            actualChild.setFirstName("test1Child");
            actualChild.setLastName("test1ParentName");
            actual.setChild1(actualChild);

            Assert.assertThat(actual, Entity.isEqualTo(expected));
        }
    }
}
