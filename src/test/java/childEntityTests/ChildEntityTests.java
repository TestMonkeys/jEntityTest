package childEntityTests;

import childEntityTests.models.ParentEntity;
import childEntityTests.models.SimpleEntity;
import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.Entity;

import java.time.LocalDateTime;

public class ChildEntityTests {

    @Test
    public void testPassing() {

        SimpleEntity childExpected = new SimpleEntity();
        childExpected.setValue(3);
        ParentEntity expected = new ParentEntity();
        expected.setChild(childExpected);
        expected.setSomeTime(LocalDateTime.now());

        SimpleEntity actualExpected = new SimpleEntity();
        actualExpected.setValue(3);
        ParentEntity actual = new ParentEntity();
        actual.setChild(actualExpected);
        actual.setSomeTime(LocalDateTime.now().minusSeconds(5));

        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailing() {
        SimpleEntity childExpected = new SimpleEntity();
        childExpected.setValue(3);
        ParentEntity expected = new ParentEntity();
        expected.setChild(childExpected);

        SimpleEntity actualExpected = new SimpleEntity();
        actualExpected.setValue(2);
        ParentEntity actual = new ParentEntity();
        actual.setChild(actualExpected);

        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }
}
