package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.util.LinkedList;
import java.util.List;


public class EntityComparator {

    private final EntityComparisonDictionary comparisonDictionary = EntityComparisonDictionary.getInstance();
    private final NullConditionalCheck nullComparatorHelper = new NullConditionalCheck();

    public List<ComparisonResult> compare(Object actual, Object expected) throws JEntityTestException {

        return compare(actual, expected, null);
    }

    public List<ComparisonResult> compare(Object actual, Object expected, ComparisonContext context) throws JEntityTestException {
        List<ComparisonResult> comparisonResults = new LinkedList<>();
        if (context == null) {
            context = new ComparisonContext();
            String parent = getContextParentName(actual, expected);
            context.setParentName(parent);
            context.setActualObj(actual);
        }

        ConditionalCheckResult conditionalCheckResult = nullComparatorHelper.runCheck(actual, expected, context);
        if (!conditionalCheckResult.isPassed() || conditionalCheckResult.stopComparison()) {
            comparisonResults.add(conditionalCheckResult);

            return comparisonResults;
        }

        ComparisonModel model = comparisonDictionary.getComparisonModel(expected.getClass());


        for (PropertyDescriptor propertyDescriptor : model.getComparableProperties()) {
            PropertyComparisonWrapper comparator = model.getComparator(propertyDescriptor);
            ComparisonContext comparisonContext;


            comparisonContext = context.withProperty(propertyDescriptor.getName());


            comparisonResults.addAll(comparator.areEqual(propertyDescriptor, actual, expected, comparisonContext));
        }
        return comparisonResults;
    }

    private String getContextParentName(Object actual, Object expected) {
        if(expected != null)
            return expected.getClass().getSimpleName();

        if(actual != null)
            return actual.getClass().getSimpleName();

        return "Entity";
    }

}
