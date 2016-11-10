package org.testmonkeys.jentitytest.inspect;

import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.property.SimpleTypeComparator;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EntityInspector {

    private final ModelToComparisonMap annotationToComparator = ModelToComparisonMap.getInstance();

    public ComparisonModel getComparisonModel(Class clazz) throws IntrospectionException, JEntityTestException {
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
                    if (!this.annotationToComparator.mapsToComparator(annotation))
                        continue;
                    model.setComparisonPoint(propertyDescriptor, this.annotationToComparator.getComparatorForAnnotation(annotation));
                    customComparison = true;
                    fieldLevelComparison = true;
                }
            } catch (NoSuchFieldException nfe) {
                //do nothing as this means that field was named differently than accessor methods
            }
            if (!fieldLevelComparison) {
                for (Annotation annotation : method.getAnnotations()) {
                    if (!this.annotationToComparator.mapsToComparator(annotation))
                        continue;
                    model.setComparisonPoint(propertyDescriptor, this.annotationToComparator.getComparatorForAnnotation(annotation));
                    customComparison = true;
                }
            }
            if (!customComparison)
                model.setComparisonPoint(propertyDescriptor, new SimpleTypeComparator());
        }
        return model;
    }
}
