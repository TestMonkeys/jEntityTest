package org.testmonkeys.jentitytest.test.childEntityTests.models;

import org.testmonkeys.jentitytest.framework.ChildEntityComparison;
import org.testmonkeys.jentitytest.framework.DateTimeComparison;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParentEntity {

    @ChildEntityComparison
    private SimpleEntity child;

    @DateTimeComparison(delta = 5, unit = ChronoUnit.SECONDS)
    private LocalDateTime someTime;

    public SimpleEntity getChild() {
        return child;
    }

    public void setChild(SimpleEntity child) {
        this.child = child;
    }

    public LocalDateTime getSomeTime() {
        return someTime;
    }

    public void setSomeTime(LocalDateTime someTime) {
        this.someTime = someTime;
    }
}
