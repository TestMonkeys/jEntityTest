package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;


public class EntityComparator {

    private List<ComparisonResult> comparisonResults;

    public EntityComparator(){
        comparisonResults=new LinkedList<>();
    }

    public List<ComparisonResult> compare(Object actual, Object expected, ComparisonModel model){
        List<ComparisonResult> result=new LinkedList<>();
        for (PropertyDescriptor propertyDescriptor : model.getComparableProperties()) {
            Comparator comparator = model.getComparator(propertyDescriptor);
            ComparisonContext context=new ComparisonContext();
            context.setParentName(propertyDescriptor.getName());
            comparisonResults.addAll(comparator.areEqual(propertyDescriptor,actual,expected, context));

        }
        return comparisonResults;
    }
}
