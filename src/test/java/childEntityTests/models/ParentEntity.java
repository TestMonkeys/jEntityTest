package childEntityTests.models;

import org.testmonkeys.jentitytest.framework.ChildEntityComparison;

public class ParentEntity {

    public SimpleEntity getChild() {
        return child;
    }

    public void setChild(SimpleEntity child) {
        this.child = child;
    }

    @ChildEntityComparison

    private SimpleEntity child;
}
