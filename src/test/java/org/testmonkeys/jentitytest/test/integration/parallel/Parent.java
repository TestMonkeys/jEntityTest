package org.testmonkeys.jentitytest.test.integration.parallel;

import org.testmonkeys.jentitytest.framework.ChildEntityComparison;

/**
 * Created by cpascal on 7/18/2017.
 */
public class Parent {

    @ChildEntityComparison

    private Child child1;
    @ChildEntityComparison
    private Child child2;
    private int age;
    private String firstName;
    private String lastName;

    public Child getChild1() {
        return child1;
    }

    public void setChild1(Child child1) {
        this.child1 = child1;
    }

    public Child getChild2() {
        return child2;
    }

    public void setChild2(Child child2) {
        this.child2 = child2;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
