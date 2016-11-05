package org.testmonkeys.jentitytest.test.edge.recursiveReference;

import org.testmonkeys.jentitytest.framework.ChildEntityComparison;

public class EntityChild {

//    @ChildEntityComparison
//    private EntityParent mom;
    @ChildEntityComparison
    private EntityParent dad;
    private int age;

    public EntityParent getDad() {
        return dad;
    }

    public void setDad(EntityParent dad) {
        this.dad = dad;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
