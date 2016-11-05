package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.util.LinkedList;
import java.util.List;


public class EntityComparator {

    private final EntityComparisonDictionary comparisonDictionary = EntityComparisonDictionary.getInstance();

    public List<ComparisonResult> compare(Object actual, Object expected) throws JEntityTestException {
        ComparisonModel comparisonModel = comparisonDictionary.getComparisonModel(expected.getClass());
        return compare(actual, expected, comparisonModel);
    }

    public List<ComparisonResult> compare(Object actual, Object expected, ComparisonContext context) throws JEntityTestException {
        ComparisonModel comparisonModel = comparisonDictionary.getComparisonModel(expected.getClass());
        return compare(actual, expected, comparisonModel, context);
    }

    public List<ComparisonResult> compare(Object actual, Object expected, ComparisonModel model) throws JEntityTestException {
        return compare(actual, expected, model, null);
    }

    public List<ComparisonResult> compare(Object actual, Object expected, ComparisonModel model, ComparisonContext context) throws JEntityTestException {
        List<ComparisonResult> comparisonResults = new LinkedList<>();
        if (context == null) {
            context = new ComparisonContext();
            context.setParentName("Entity");
        }
        for (PropertyDescriptor propertyDescriptor : model.getComparableProperties()) {
            Comparator comparator = model.getComparator(propertyDescriptor);
            ComparisonContext comparisonContext;


            comparisonContext = context.withProprety(propertyDescriptor.getName());


            comparisonResults.addAll(comparator.areEqual(propertyDescriptor, actual, expected, comparisonContext));
        }
        return comparisonResults;
    }

}
