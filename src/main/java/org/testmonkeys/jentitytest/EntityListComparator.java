package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.abortConditions.AbstractAbortCondition;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.strategies.ChildEntityListComparator;
import org.testmonkeys.jentitytest.comparison.validations.AbstractValidation;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.model.ComparisonModel;
import org.testmonkeys.jentitytest.model.EntityToComparisonModelDictionary;

import java.beans.PropertyDescriptor;

import static org.testmonkeys.jentitytest.Resources.entity;
import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;


public class EntityListComparator {
    private final NullConditionalCheck nullComparatorHelper = new NullConditionalCheck();

    /**
     * @param actual   actual object list
     * @param expected expected object list
     * @param ordered if set to false will ignore the order of the items in the lists
     * @return result set of the comparison
     * @throws JEntityTestException in case the comparison can't be completed
     */
    public ResultSet compare(Object actual, Object expected, boolean ordered) {
        ComparisonContext context=new ComparisonContext();
        context.setParentName("List");
        return compare(actual, expected, context, ordered);
    }

    /**
     * @param actual   actual object list
     * @param expected expected object list
     * @return result set of the comparison
     * @throws JEntityTestException in case the comparison can't be completed
     */
    public ResultSet compare(Object actual, Object expected) {
        ComparisonContext context=new ComparisonContext();
        context.setParentName("List");
        return compare(actual, expected, context);
    }

    /**
     * @param actual actual object list
     * @param expected expected object list
     * @param comparisonContext comparison context of the current entities
     * @return result set of the comparison
     * @throws JEntityTestException in case the comparison can't be completed
     */
    public ResultSet compare(Object actual, Object expected, ComparisonContext comparisonContext) {
        return compare(actual,expected,comparisonContext,true);
    }

    /**
     * @param actual actual object list
     * @param expected expected object list
     * @param comparisonContext comparison context of the current entities
     * @param ordered if set to false will ignore the order of the items in the lists
     * @return result set of the comparison
     * @throws JEntityTestException in case the comparison can't be completed
     */
    public ResultSet compare(Object actual, Object expected, ComparisonContext comparisonContext, boolean ordered) {
        ComparisonContext context = comparisonContext;

        ConditionalCheckResult conditionalCheckResult = nullComparatorHelper.runCheck(actual, expected, context);
        if ((conditionalCheckResult.getStatus() == Failed) || conditionalCheckResult.isComparisonFinished()) {
            return new ResultSet().with(conditionalCheckResult);
        }

        ResultSet comparisonResults = new ResultSet();
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        comparator.setOrdered(ordered);
        comparisonResults.addAll(comparator.compare(actual,expected,comparisonContext));
        return comparisonResults;
    }


}
