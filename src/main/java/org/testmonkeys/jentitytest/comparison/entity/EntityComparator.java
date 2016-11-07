package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.util.NullComparison;
import org.testmonkeys.jentitytest.comparison.util.NullComparisonResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.util.LinkedList;
import java.util.List;


public class EntityComparator {

    private final EntityComparisonDictionary comparisonDictionary = EntityComparisonDictionary.getInstance();
    private final NullComparison nullComparisonHelper = new NullComparison();

    public List<ComparisonResult> compare(Object actual, Object expected) throws JEntityTestException {

        return compare(actual, expected, null);
    }

    public List<ComparisonResult> compare(Object actual, Object expected, ComparisonContext context) throws JEntityTestException {
        List<ComparisonResult> comparisonResults = new LinkedList<>();
        if (context == null) {
            context = new ComparisonContext();
            context.setParentName("Entity");
            context.setActualObj(actual);
        }

        NullComparisonResult nullComparisonResult = nullComparisonHelper.compareOnNulls(actual, expected, context);
        if (!nullComparisonResult.isPassed() || nullComparisonResult.isStopComparison()) {
            comparisonResults.add(nullComparisonResult);
            return comparisonResults;
        }

        ComparisonModel model = comparisonDictionary.getComparisonModel(expected.getClass());


        for (PropertyDescriptor propertyDescriptor : model.getComparableProperties()) {
            Comparator comparator = model.getComparator(propertyDescriptor);
            ComparisonContext comparisonContext;


            comparisonContext = context.withProprety(propertyDescriptor.getName());


            comparisonResults.addAll(comparator.areEqual(propertyDescriptor, actual, expected, comparisonContext));
        }
        return comparisonResults;
    }

}
