package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.PreComparisonCheck;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.model.ComparisonModel;
import org.testmonkeys.jentitytest.model.EntityToComparisonModelDictionary;

import java.beans.PropertyDescriptor;

import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;


public class EntityComparator {

    private final EntityToComparisonModelDictionary comparisonDictionary =
            EntityToComparisonModelDictionary.getInstance();
    private final PreComparisonCheck nullComparatorHelper = new NullConditionalCheck();

    /**
     * @param actual
     * @param expected
     * @return
     * @throws JEntityTestException
     */
    public ResultSet compare(Object actual, Object expected) {

        return compare(actual, expected, null);
    }

    /**
     * @param actual
     * @param expected
     * @param context
     * @return
     * @throws JEntityTestException
     */
    public ResultSet compare(Object actual, Object expected, ComparisonContext comparisonContext) {
        ComparisonContext context = comparisonContext;
        if (context == null) {
            context = new ComparisonContext();
            String parent = getContextParentName(actual, expected);
            context.setParentName(parent);
            context.setActualObj(actual);
        }

        ConditionalCheckResult conditionalCheckResult = nullComparatorHelper.runCheck(actual, expected, context);
        if ((conditionalCheckResult.getStatus() == Failed) || conditionalCheckResult.isComparisonFinished()) {
            return new ResultSet().with(conditionalCheckResult);
        }

        ComparisonModel model = comparisonDictionary.getComparisonModel(expected.getClass());

        ResultSet comparisonResults = new ResultSet();
        for (PropertyDescriptor propertyDescriptor : model.getComparableProperties()) {
            PropertyComparisonWrapper comparator = model.getComparator(propertyDescriptor);
            ComparisonContext propContext = context.withProperty(propertyDescriptor.getName());
            comparisonResults.addAll(comparator.compare(propertyDescriptor, actual, expected, propContext));
        }
        return comparisonResults;
    }

    private String getContextParentName(Object actual, Object expected) {
        if (expected != null)
            return expected.getClass().getSimpleName();

        if (actual != null)
            return actual.getClass().getSimpleName();

        return "Entity";
    }

}
