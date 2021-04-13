package org.testmonkeys.jentitytest.test.integration.validations.validateRegexp;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;
import org.testmonkeys.jentitytest.framework.ValidateRegex;

public class SimpleModelWithRegexp {


    private String name;
    private int age;

    public SimpleModelWithRegexp(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @IgnoreComparison
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ValidateRegex(expression = "J..n")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
