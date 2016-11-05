package org.testmonkeys.jentitytest.test.edge.recursiveReference;

import org.testmonkeys.jentitytest.framework.ChildEntityComparison;

public class EntityParent {

    @ChildEntityComparison
    private EntityChild firstChild;

    public EntityChild getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(EntityChild firstChild) {
        this.firstChild = firstChild;
    }
}
