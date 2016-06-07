import junit.framework.AssertionFailedError;
import models.SimpleModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testmonkeys.jentitytest.Entity;
import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.impl.SimpleTypeCoparator;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;


/**
 * Created by cpascal on 6/7/2016.
 */
public class TestClass {

    @Before
    public void modelAndDictionarySetup() throws IntrospectionException {

        ComparisonModel model = new ComparisonModel();
        for(PropertyDescriptor propertyDescriptor :
                Introspector.getBeanInfo(SimpleModel.class).getPropertyDescriptors()) {
            model.setComparisonPoint(propertyDescriptor, new SimpleTypeCoparator());
        }
        EntityComparisonDictionary.getInstance().addComparisonModel(SimpleModel.class,model);
    }

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
