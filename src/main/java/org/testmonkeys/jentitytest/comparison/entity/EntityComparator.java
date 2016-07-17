package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;


public class EntityComparator{

    private final EntityComparisonDictionary comparisonDictionary = EntityComparisonDictionary.getInstance();

    public List<ComparisonResult> compare(Object actual, Object expected){
        ComparisonModel comparisonModel = comparisonDictionary.getComparisonModel(expected.getClass());
        return compare(actual,expected,comparisonModel);
    }

    public List<ComparisonResult> compare(Object actual, Object expected, ComparisonContext context){
        ComparisonModel comparisonModel = comparisonDictionary.getComparisonModel(expected.getClass());
        return compare(actual,expected,comparisonModel,context);
    }

    public List<ComparisonResult> compare(Object actual, Object expected, ComparisonModel model){
        return compare(actual,expected,model,null);
    }

    public List<ComparisonResult> compare(Object actual, Object expected, ComparisonModel model, ComparisonContext context){
        List<ComparisonResult> comparisonResults=new LinkedList<>();
        for (PropertyDescriptor propertyDescriptor : model.getComparableProperties()) {
            Comparator comparator = model.getComparator(propertyDescriptor);
            ComparisonContext comparisonContext;
            if (context==null) {
                comparisonContext = new ComparisonContext();
                comparisonContext.setParentName(propertyDescriptor.getName());
            }else
                comparisonContext=context.withProprety(propertyDescriptor.getName());

            comparisonResults.addAll(comparator.areEqual(propertyDescriptor,actual,expected, comparisonContext));
        }
        return comparisonResults;
    }

}
