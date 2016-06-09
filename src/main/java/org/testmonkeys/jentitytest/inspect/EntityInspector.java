package org.testmonkeys.jentitytest.inspect;

import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.impl.SimpleTypeCoparator;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Created by cpascal on 6/9/2016.
 */
public class EntityInspector {

    public ComparisonModel getComparisonModel(Object obj) throws IntrospectionException {
        return getComparisonModel(obj.getClass());
    }

    public ComparisonModel getComparisonModel(Class clazz) throws IntrospectionException {
        ComparisonModel model = new ComparisonModel();
        for(PropertyDescriptor propertyDescriptor :
                Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
            model.setComparisonPoint(propertyDescriptor, new SimpleTypeCoparator());
        }
        return model;
    }
}
