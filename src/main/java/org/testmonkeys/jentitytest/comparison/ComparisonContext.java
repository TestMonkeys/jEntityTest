package org.testmonkeys.jentitytest.comparison;

public class ComparisonContext {
    private ComparisonContext parent;
    private String parentName;
    private int index;
    private Object actualObj;
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
        comparisonContext.setParentName(propertyName);
        return comparisonContext;
    }

    /**
     * Generates the full path for current comparison
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (parent != null) {
            sb.append(parent).append(".");
        }
        sb.append(getParentName());
        if (index != -1)
            sb.append("[").append(index).append("]");
        return sb.toString();
    }

    /**
     * Checks if the object was present before in the ComparisonContext tree
     * @param actual
     * @return
     */
    public boolean isRecursive(Object actual) {
        return canBeRecursive(actual) &&
                (actual.equals(parent.getActualObj()) || parent.isRecursive(actual));
    }

    private boolean canBeRecursive(Object actual){
        return parent !=null && actual !=null;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getComparatorDetails() {
        return comparatorDetails;
    }

    public void setComparatorDetails(String comparatorDetails) {
        this.comparatorDetails = comparatorDetails;
    }

    public String getParentName() {
        return parentName;
    }

    public Object getActualObj() {
        return actualObj;
    }
}
