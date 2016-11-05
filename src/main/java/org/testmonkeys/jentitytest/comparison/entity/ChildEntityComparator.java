package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.MultiResultComparator;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ChildEntityComparator extends MultiResultComparator {

    private List<ComparisonResult> comparisonResults;

    public ChildEntityComparator(){
        comparisonResults=new LinkedList<>();
    }

    @Override
    protected List<ComparisonResult> computeComparison(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) throws JEntityTestException {


        Object actualValue=getPropertyValue(property,actual);
        Object expectedValue = getPropertyValue(property,expected);

        if (actualValue==null && expectedValue==null)
            return new ArrayList<>();
        if (actualValue!=null && expectedValue==null || actualValue==null && expectedValue!=null) {
            List<ComparisonResult> results= new ArrayList<>();
            ComparisonResult result = new ComparisonResult();
            result.setPassed(false);
            result.setActual(actualValue==null?"null":"not null");
            result.setExpected(expectedValue==null?"null":"not null");
            result.setComparisonContext(context);
            results.add(result);
            return results;
        }

        if (context.isRecursive(actualValue))
            return new ArrayList<>();
        context.setActualObj(actualValue);
        EntityComparator comparator = new EntityComparator();
        return comparator.compare(actualValue,expectedValue,context);

    }
}
