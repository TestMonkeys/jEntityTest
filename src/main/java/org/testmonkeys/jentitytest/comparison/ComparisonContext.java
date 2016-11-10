package org.testmonkeys.jentitytest.comparison;

public class ComparisonContext {
    private ComparisonContext parent;
    private String parentName;
    private int index;
    private Object actualObj;

    public ComparisonContext() {
        this.index = -1;
    }

    public ComparisonContext(ComparisonContext parent) {
        this.parent = parent;
        this.index = -1;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setActualObj(Object actualObj) {
        this.actualObj = actualObj;
    }

    public ComparisonContext withProperty(String propertyName) {
        ComparisonContext comparisonContext = new ComparisonContext(this);
        comparisonContext.index = this.index;
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
        if (this.parent != null) {
            sb.append(this.parent).append(".");
        }
        sb.append(this.parentName);
        if (this.index != -1)
            sb.append("[").append(this.index).append("]");
        return sb.toString();
    }

    public boolean isRecursive(Object actual) {
        if (this.parent == null || actual == null) return false;
        return actual.equals(this.parent.actualObj) || this.parent.isRecursive(actual);
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
