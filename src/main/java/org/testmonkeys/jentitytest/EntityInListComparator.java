package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.abortConditions.AbstractAbortCondition;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;
import org.testmonkeys.jentitytest.comparison.validations.AbstractValidation;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.model.ComparisonModel;
import org.testmonkeys.jentitytest.model.EntityToComparisonModelDictionary;

import java.beans.PropertyDescriptor;
import java.util.Collection;

import static org.testmonkeys.jentitytest.Resources.entity;
import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;


public class EntityInListComparator<T> {

    private final EntityToComparisonModelDictionary comparisonDictionary =
            EntityToComparisonModelDictionary.getInstance();
    private final NullConditionalCheck nullComparatorHelper = new NullConditionalCheck();

    /**
     * @param entityList collection in which to search for the expected object
     * @param expected   expected object
     * @return result set of the comparison
     * @throws JEntityTestException in case the comparison can't be completed
     */
    public ResultSet entityInList(T expected, Collection<T> entityList) {
        ResultSet potentialResult = new ResultSet();

        if (entityList==null){
            ComparisonContext context = new ComparisonContext();
            context.setParentName("Collection");
            ConditionalCheckResult conditionalCheckResult = new ConditionalCheckResult(Failed, context, "null", "not null");
            return new ResultSet().with(conditionalCheckResult);
        }

        if (!entityList.iterator().hasNext()) {
            ComparisonContext context = new ComparisonContext();
            context.setParentName("Collection");
            ConditionalCheckResult conditionalCheckResult = new ConditionalCheckResult(Failed, context, "empty", "not empty");
            return new ResultSet().with(conditionalCheckResult);
        }

        for (T item : entityList) {
            ResultSet itemComparisonResult = compareToListItem(expected, item);
            if (itemComparisonResult.isPerfectMatch())
                return itemComparisonResult;
            potentialResult.replaceIfBetterMatch(itemComparisonResult);
        }
        return potentialResult;
    }

    private ResultSet compareToListItem(Object expected, T actualItem) {
        ResultSet result = new ResultSet();
        EntityComparator comparator = new EntityComparator();
        result.addAll(comparator.compare(actualItem, expected));
        return result;
    }

}
