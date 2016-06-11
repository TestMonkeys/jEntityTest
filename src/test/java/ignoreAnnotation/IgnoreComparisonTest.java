package ignoreAnnotation;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.Entity;


/**
 * Created by cpascal on 6/7/2016.
 */
public class IgnoreComparisonTest {

    @Test
    public void testPassing(){

        SimpleModelWithIgnore expected=new SimpleModelWithIgnore("John",26);
        SimpleModelWithIgnore actual=new SimpleModelWithIgnore("John",27);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailing(){

        SimpleModelWithIgnore expected=new SimpleModelWithIgnore("Johan",26);
        SimpleModelWithIgnore actual=new SimpleModelWithIgnore("John",26);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }
}
