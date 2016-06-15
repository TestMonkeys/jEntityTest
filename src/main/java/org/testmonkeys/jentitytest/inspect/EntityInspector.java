package org.testmonkeys.jentitytest.inspect;

import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.property.SimpleTypeComparator;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;

public class EntityInspector {

    private final ModelToComparisonMap annotationToComparator = ModelToComparisonMap.getInstance();

    public ComparisonModel getComparisonModel(Object obj) throws IntrospectionException {
        return getComparisonModel(obj.getClass());
    }

    public ComparisonModel getComparisonModel(Class clazz) throws IntrospectionException {
        ComparisonModel model = new ComparisonModel();
        for (PropertyDescriptor propertyDescriptor :
                Introspector.getBeanInfo(clazz,Object.class).getPropertyDescriptors()) {
            boolean customComparison = false;
            for (Annotation annotation : propertyDescriptor.getReadMethod().getAnnotations()) {

                model.setComparisonPoint(propertyDescriptor, annotationToComparator.getComparatorForAnnotation(annotation));
                customComparison = true;
            }
            if (!customComparison)
                model.setComparisonPoint(propertyDescriptor, new SimpleTypeComparator());
        }
        return model;
    }
}
