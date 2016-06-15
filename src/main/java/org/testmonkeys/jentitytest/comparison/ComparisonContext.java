package org.testmonkeys.jentitytest.comparison;

public class ComparisonContext {
    private ComparisonContext parent;

    public ComparisonContext()
    {
        index=-1;
    }

    public ComparisonContext(ComparisonContext parent)
    {
        this.parent = parent;
        index=-1;
    }

    private String parentName;

    public void setIndex(int index) {
        this.index = index;
    }

    private int index;

    public void setActualObj(Object actualObj) {
        this.actualObj = actualObj;
    }

    private Object actualObj;


    public ComparisonContext withProprety(String propertyName)
    {
        ComparisonContext comparisonContext = new ComparisonContext(this);
        comparisonContext.index=index;
        comparisonContext.parentName=propertyName;
        return comparisonContext;
    }

    public ComparisonContext withIndex(int index)
    {
        ComparisonContext comparisonContext = new ComparisonContext(this);
        comparisonContext.index=index;
        return comparisonContext;

    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if (parent != null)
        {
            sb.append(parent).append(".");
        }
        sb.append(parentName);
        if (index!=-1)
            sb.append("[").append(index).append("]");
        return sb.toString();
    }

    public boolean isRecursive(Object actual)
    {
        if (parent == null) return false;
        return actual.equals(parent.actualObj) || parent.isRecursive(actual);
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
