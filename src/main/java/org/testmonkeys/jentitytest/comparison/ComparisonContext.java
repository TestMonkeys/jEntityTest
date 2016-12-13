package org.testmonkeys.jentitytest.comparison;

import java.beans.PropertyDescriptor;

public class ComparisonContext {
    private ComparisonContext parent;
    private String parentName;
    private int index;
    private Object actualObj;
    private PropertyDescriptor propertyDescriptor;
    private String comparatorDetails;

    public ComparisonContext() {
        index = -1;
    }

    public ComparisonContext(ComparisonContext parent) {
        this.parent = parent;
        index = -1;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setActualObj(Object actualObj) {
        this.actualObj = actualObj;
    }

    public ComparisonContext withProperty(String propertyName) {
        ComparisonContext comparisonContext = new ComparisonContext(this);
        comparisonContext.parentName = propertyName;
        return comparisonContext;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (parent != null) {
            sb.append(parent).append(".");
        }
        sb.append(parentName);
        if (index != -1)
            sb.append("[").append(index).append("]");
        return sb.toString();
    }

    public boolean isRecursive(Object actual) {
        return !(parent == null || actual == null) &&
                (actual.equals(parent.actualObj) || parent.isRecursive(actual));
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setPropertyDescriptor(PropertyDescriptor propertyDescriptor) {
        this.propertyDescriptor = propertyDescriptor;
    }

    public String getComparatorDetails() {
        return comparatorDetails;
    }

    public void setComparatorDetails(String comparatorDetails) {
        this.comparatorDetails = comparatorDetails;
    }
}
