package noAnnotations;

import noAnnotations.SimpleModel;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.Entity;


/**
 * Created by cpascal on 6/7/2016.
 */
public class TestClass {

    @Test
    public void testPassing(){

        SimpleModel expected=new SimpleModel("John",26);
        SimpleModel actual=new SimpleModel("John",26);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void testFailing(){

        SimpleModel expected=new SimpleModel("Johan",26);
        SimpleModel actual=new SimpleModel("John",26);
        Assert.assertThat(actual, Entity.isEqualTo(expected));
    }
}
