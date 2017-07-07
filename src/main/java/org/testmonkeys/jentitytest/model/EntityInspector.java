package org.testmonkeys.jentitytest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.strategies.SimpleTypeComparator;
import org.testmonkeys.jentitytest.exceptions.JEntityModelException;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.testmonkeys.jentitytest.Resources.err_getting_beaninfo_from_class;

public class EntityInspector {

    private static final Logger LOG = LoggerFactory.getLogger(EntityInspector.class);
    private final AnnotationToComparatorDictionary annotationToComparator =
            AnnotationToComparatorDictionary.getInstance();

    /**
     * @param clazz
     * @return
     * @throws JEntityTestException
     */
    @SuppressWarnings("ObjectAllocationInLoop")
    public ComparisonModel getComparisonModel(Class clazz) {
        LOG.info("Starting inspection for {}",clazz); //LOG
        ComparisonModel model = new ComparisonModel();
        BeanInfo beanInfo = getBeanInfo(clazz);

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            LOG.info("Analyzing property {}",propertyDescriptor.getName()); //LOG
            Method method = propertyDescriptor.getReadMethod();
            if (method == null){
                LOG.info("property does not have read method, skipping..."); //LOG
                continue;
            }
            Field field = getField(clazz, propertyDescriptor);

            //Field level processing
            if (field != null) {
                Annotation annotation = getComparisonAnnotation(field);
                if (annotation != null) {
                    LOG.info("Found annotation at field level"); //LOG
                    model.setComparisonPoint(propertyDescriptor, getPropertyComparator(annotation));
                    continue;
                }
            }

            //Method level processing
            Annotation annotation = getComparisonAnnotation(method);
            if (annotation != null) {
                LOG.info("Found annotation at method level"); //LOG
                model.setComparisonPoint(propertyDescriptor, getPropertyComparator(annotation));
                continue;
            }

            //Default (in case no annotations were used)
            LOG.info("No annotation found, using default comparator"); //LOG
            model.setComparisonPoint(propertyDescriptor, new PropertyComparisonWrapper(new SimpleTypeComparator()));
        }
        return model;
    }

    private BeanInfo getBeanInfo(Class clazz) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        } catch (IntrospectionException e) {
            throw new JEntityModelException(MessageFormat.format(
                    Resources.getString(err_getting_beaninfo_from_class), clazz), e);
        }
        return beanInfo;
    }

    private Annotation getComparisonAnnotation(AnnotatedElement member) {
        List<Annotation> knownAnnotations = new ArrayList<>();
        for (Annotation candidate : member.getAnnotations()) {
            if (annotationToComparator.hasComparatorAssigned(candidate)) {
                knownAnnotations.add(candidate);
            }
        }
        if (knownAnnotations.size() > 1)
            throw new JEntityModelException("There should be only one Comparison Annotation on your model");
        if (knownAnnotations.size() == 1)
            return knownAnnotations.get(0);
        else
            return null;
    }

    private PropertyComparisonWrapper getPropertyComparator(Annotation annotation) {
        return new PropertyComparisonWrapper(annotationToComparator.getComparatorForAnnotation(annotation));
    }

    private Field getField(Class clazz, PropertyDescriptor propertyDescriptor) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(propertyDescriptor.getName());
        } catch (NoSuchFieldException ignored) {
            //do nothing as this means that field was named differently than accessor methods
        }
        return field;
    }
}
