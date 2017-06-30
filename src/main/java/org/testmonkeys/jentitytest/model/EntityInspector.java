package org.testmonkeys.jentitytest.model;

import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.strategies.SimpleTypeComparator;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EntityInspector {

    private final AnnotationToComparatorDictionary annotationToComparator = AnnotationToComparatorDictionary.getInstance();

    /**
     * @param clazz
     * @return
     * @throws JEntityTestException
     */
    public ComparisonModel getComparisonModel(Class clazz) {
        ComparisonModel model = new ComparisonModel();
        BeanInfo beanInfo = getBeanInfo(clazz);

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            Method method = propertyDescriptor.getReadMethod();
            if (method == null)
                continue;
            Field field = getField(clazz, propertyDescriptor);

            //Field level processing
            if (field!=null) {
                for (Annotation annotation : getKnownAnnotations(field)) {
                    model.setComparisonPoint(propertyDescriptor, getPropertyComparator(annotation));
                    break;
                }
            }
            //Method level processing
            if (!model.hasComparisonPoint(propertyDescriptor)) {
                for (Annotation annotation : getKnownAnnotations(method)) {
                    model.setComparisonPoint(propertyDescriptor, getPropertyComparator(annotation));
                    break;
                }
            }
            //Default (in case no annotations were used)
            if (!model.hasComparisonPoint(propertyDescriptor))
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

    private List<Annotation> getKnownAnnotations(AnnotatedElement member) {
        List<Annotation> knownAnnotations = new ArrayList<>();
        for (Annotation candidate : member.getAnnotations()) {
            if (this.annotationToComparator.hasComparatorAssinged(candidate)) {
                knownAnnotations.add(candidate);
            }
        }
        return knownAnnotations;
    }

    private PropertyComparisonWrapper getPropertyComparator(Annotation annotation){
        return new PropertyComparisonWrapper(this.annotationToComparator.getComparatorForAnnotation(annotation));
    }

    private Field getField(Class clazz, PropertyDescriptor propertyDescriptor){
        Field field=null;
        try {
            field = clazz.getDeclaredField(propertyDescriptor.getName());
        } catch (NoSuchFieldException nfe) {
            //do nothing as this means that field was named differently than accessor methods
        }
        return field;
    }
}
