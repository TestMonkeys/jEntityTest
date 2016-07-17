package org.testmonkeys.jentitytest.comparison;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public abstract class MultiResultComparator implements Comparator {

    public List<ComparisonResult> areEqual(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context){
        List<ComparisonResult> resultList =new LinkedList<>();

        //TODO check if not recursive


        resultList.addAll(computeComparison(property,actual,expected,context));

        return resultList;
    }

    protected Object getPropertyValue(PropertyDescriptor property, Object obj){
        try {
            //TODO: think how to handle such exceptions
            return property.getReadMethod().invoke(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract List<ComparisonResult> computeComparison(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context);
}
