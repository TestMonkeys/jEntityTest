package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.comparison.*;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.beans.PropertyDescriptor;
import java.util.LinkedList;
import java.util.List;


public class ChildEntityComparator extends MultiResultComparator {

    private List<ComparisonResult> comparisonResults;

    public ChildEntityComparator(){
        comparisonResults=new LinkedList<>();
    }

    @Override
    protected List<ComparisonResult> computeComparison(PropertyDescriptor property, Object actual, Object expected, ComparisonContext context) throws JEntityTestException {
        ComparisonContext comparisonContext=context.withProprety(property.getName());

        Object actualValue=getPropertyValue(property,actual);
        Object expectedValue = getPropertyValue(property,expected);

        EntityComparator comparator = new EntityComparator();
        return comparator.compare(actualValue,expectedValue,comparisonContext);

    }
}
