package org.testmonkeys.jentitytest.test.integration.bugs.issue_15;

import org.testmonkeys.jentitytest.framework.ChildEntityComparison;

public class EntityParent {

    @ChildEntityComparison
    private EntityChild firstChild;

    public EntityChild getFirstChild() {
        return this.firstChild;
    }

    public void setFirstChild(EntityChild firstChild) {
        this.firstChild = firstChild;
    }
}
