package org.testmonkeys.jentitytest.inspect;

import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.property.SimpleTypeComparator;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EntityInspector {

    private final ModelToComparisonMap annotationToComparator = ModelToComparisonMap.getInstance();

    public ComparisonModel getComparisonModel(Object obj) throws IntrospectionException {
        return getComparisonModel(obj.getClass());
    }

    public ComparisonModel getComparisonModel(Class clazz) throws IntrospectionException {
        ComparisonModel model = new ComparisonModel();
        for (PropertyDescriptor propertyDescriptor :
                Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors()) {
            Method method = propertyDescriptor.getReadMethod();
            if (method == null)
                continue;
            boolean customComparison = false;
            boolean fieldLevelComparison = false;
            try {
                Field field = clazz.getDeclaredField(propertyDescriptor.getName());
                for (Annotation annotation : field.getAnnotations()) {
                    model.setComparisonPoint(propertyDescriptor, annotationToComparator.getComparatorForAnnotation(annotation));
                    customComparison = true;
                    fieldLevelComparison = true;
                }
            } catch (NoSuchFieldException nfe) {

            }
            if (!fieldLevelComparison) {

                for (Annotation annotation : method.getAnnotations()) {
                    model.setComparisonPoint(propertyDescriptor, annotationToComparator.getComparatorForAnnotation(annotation));
                    customComparison = true;
                }
            }
            if (!customComparison)
                model.setComparisonPoint(propertyDescriptor, new SimpleTypeComparator());
        }
        return model;
    }
}
