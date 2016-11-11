package org.testmonkeys.jentitytest.comparison;

public class ComparisonContext {
    private ComparisonContext parent;
    private String parentName;
    private int index;
    private Object actualObj;

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
        comparisonContext.index = index;
        comparisonContext.parentName = propertyName;
        return comparisonContext;
    }

    public ComparisonContext withIndex(int index) {
        ComparisonContext comparisonContext = new ComparisonContext(this);
        comparisonContext.index = index;
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
        if (parent == null || actual == null) return false;
        return actual.equals(parent.actualObj) || parent.isRecursive(actual);
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
