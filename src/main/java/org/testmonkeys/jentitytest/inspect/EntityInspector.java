package org.testmonkeys.jentitytest.inspect;

import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.property.SimpleTypeComparator;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EntityInspector {

    private final ModelToComparisonMap annotationToComparator = ModelToComparisonMap.getInstance();

    public ComparisonModel getComparisonModel(Class clazz) throws JEntityTestException {
        ComparisonModel model = new ComparisonModel();
        BeanInfo beanInfo = getBeanInfo(clazz);

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            Method method = propertyDescriptor.getReadMethod();
            if (method == null)
                continue;
            boolean customComparison = false;
            boolean fieldLevelComparison = false;
            try {
                Field field = clazz.getDeclaredField(propertyDescriptor.getName());
                for (Annotation annotation : field.getAnnotations()) {
                    if (annotationToComparator.mapsToComparator(annotation)) {

                        model.setComparisonPoint(propertyDescriptor,
                                new PropertyComparisonWrapper(annotationToComparator.getComparatorForAnnotation(annotation)));

                        customComparison = true;
                        fieldLevelComparison = true;
                    }
                }
            } catch (NoSuchFieldException nfe) {
                //do nothing as this means that field was named differently than accessor methods
            }
            if (!fieldLevelComparison) {
                for (Annotation annotation : method.getAnnotations()) {
                    if (annotationToComparator.mapsToComparator(annotation)) {
                        model.setComparisonPoint(propertyDescriptor,
                                new PropertyComparisonWrapper(annotationToComparator.getComparatorForAnnotation(annotation)));

                        customComparison = true;
                    }
                }
            }
            if (!customComparison)
                model.setComparisonPoint(propertyDescriptor, new PropertyComparisonWrapper(new SimpleTypeComparator()));
        }
        return model;
    }

    private BeanInfo getBeanInfo(Class clazz) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        } catch (IntrospectionException e) {
            throw new JEntityTestException("Could not get BeanInfo from class: " + clazz);
        }
        return beanInfo;
    }
}
