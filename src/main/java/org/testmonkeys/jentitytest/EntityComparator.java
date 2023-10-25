package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.PropertyComparisonWrapper;
import org.testmonkeys.jentitytest.comparison.abortConditions.AbstractAbortCondition;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ConditionalCheckResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.validations.AbstractValidation;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.model.ComparisonModel;
import org.testmonkeys.jentitytest.model.EntityToComparisonModelDictionary;

import java.beans.PropertyDescriptor;
import java.util.Collection;

import static org.testmonkeys.jentitytest.Resources.entity;
import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;


public class EntityComparator {

    private final EntityToComparisonModelDictionary comparisonDictionary =
            EntityToComparisonModelDictionary.getInstance();
    private final NullConditionalCheck nullComparatorHelper = new NullConditionalCheck();

    /**
     * @param actual   actual object
     * @param expected expected object
     * @return result set of the comparison
     * @throws JEntityTestException in case the comparison can't be completed
     */
    public ResultSet compare(Object actual, Object expected) {

        return compare(actual, expected, null);
    }

    /**
     * @param actual actual object
     * @param expected expected object
     * @param comparisonContext comparison context of the current entities
     * @return result set of the comparison
     * @throws JEntityTestException in case the comparison can't be completed
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
        if (expected instanceof Collection)
            throw new JEntityTestException("comparison on lists should be done using EntityList Comparison");

        ComparisonModel model = comparisonDictionary.getComparisonModel(expected.getClass());

        ResultSet comparisonResults = new ResultSet();
        for (PropertyDescriptor propertyDescriptor : model.getComparableProperties()) {
            ComparisonContext propContext = context.withProperty(propertyDescriptor.getName());
            ResultSet results = runChecksForField(model, propertyDescriptor, actual, expected, propContext);
            comparisonResults.addAll(results);
        }
        return comparisonResults;
    }

    private ResultSet runChecksForField(ComparisonModel model, PropertyDescriptor propertyDescriptor, Object actual, Object expected, ComparisonContext propContext) {
        //Validations
        if (model.hasValidations(propertyDescriptor))
            for (AbstractValidation validation : model.getValidations(propertyDescriptor)) {
                ConditionalCheckResult conditionalCheckResult = validation.check(propertyDescriptor, actual, expected, propContext);
                if ((conditionalCheckResult.getStatus() == Failed) || conditionalCheckResult.isComparisonFinished()) {
                    return new ResultSet().with(conditionalCheckResult);
                }
            }

        //Abort Conditions block
        if (model.hasAbortConditions(propertyDescriptor))
            for (AbstractAbortCondition preConditionalChecks : model.getAbortConditionChecks(propertyDescriptor)) {
                ConditionalCheckResult conditionalCheckResult = preConditionalChecks.check(propertyDescriptor, actual, expected, propContext);
                if ((conditionalCheckResult.getStatus() == Failed) || conditionalCheckResult.isComparisonFinished()) {
                    return new ResultSet().with(conditionalCheckResult);
                }
            }

        //comparison block
        PropertyComparisonWrapper comparator = model.getComparator(propertyDescriptor);
        return comparator.compare(propertyDescriptor, actual, expected, propContext);
    }

    private String getContextParentName(Object actual, Object expected) {
        if (expected != null)
            return expected.getClass().getSimpleName();

        if (actual != null)
            return actual.getClass().getSimpleName();

        return Resources.getString(entity);
    }

}
