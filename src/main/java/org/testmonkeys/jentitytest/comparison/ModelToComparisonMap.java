package org.testmonkeys.jentitytest.comparison;

import org.testmonkeys.jentitytest.comparison.impl.IgnoreComparator;
import org.testmonkeys.jentitytest.comparison.impl.SimpleTypeComparator;
import org.testmonkeys.jentitytest.framework.IgnoreComparison;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpascal on 6/11/2016.
 */
public class ModelToComparisonMap {

    private static ModelToComparisonMap instance;

    public static ModelToComparisonMap getInstance(){
        if (instance==null)
            instance=new ModelToComparisonMap();
        return instance;
    }

    private Map<Class,Class> mapping;

    private ModelToComparisonMap(){
        mapping = new HashMap<>();
        mapping.put(IgnoreComparison.class, IgnoreComparator.class);
    }

    public Comparator getComparatorForAnnotation(Annotation annotation){

        if (mapping.containsKey(annotation.annotationType()))
            try {
                return (Comparator) mapping.get(annotation.annotationType()).getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        return new SimpleTypeComparator();
    }
}
