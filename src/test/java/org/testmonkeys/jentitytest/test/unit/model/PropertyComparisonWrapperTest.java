package org.testmonkeys.jentitytest.test.unit.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.strategies.SimpleTypeComparator;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.test.unit.model.util.Model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import static java.beans.Introspector.getBeanInfo;

public class PropertyComparisonWrapperTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void throwIllegalAccessExceptionTest() throws IntrospectionException {
        this.expectedException.expect(JEntityTestException.class);
        this.expectedException.expectMessage("Could not read property from object");
        PropertyComparisonWrapper propertyComparison = new PropertyComparisonWrapper(new SimpleTypeComparator());
        BeanInfo beanInfo = getBeanInfo(Model.class);
        PropertyDescriptor descriptor = null;
        for(PropertyDescriptor d : beanInfo.getPropertyDescriptors()) {
            if(d.getName().equals("illegalAccess"))
                descriptor = d;
        }
        propertyComparison.areEqual(descriptor, new Model(), new Model(), new ComparisonContext());
    }

    @Test
    public void throwInvocationTargetExceptionTest() throws IntrospectionException {
        this.expectedException.expect(JEntityTestException.class);
        this.expectedException.expectMessage("Could not read property from object");
        PropertyComparisonWrapper propertyComparison = new PropertyComparisonWrapper(new SimpleTypeComparator());
        BeanInfo beanInfo = getBeanInfo(Model.class);
        PropertyDescriptor descriptor = null;
        for(PropertyDescriptor d : beanInfo.getPropertyDescriptors()) {
            if(d.getName().equals("invocationTarget"))
                descriptor = d;
        }
        propertyComparison.areEqual(descriptor, new Model(), new Model(), new ComparisonContext());
    }
}
