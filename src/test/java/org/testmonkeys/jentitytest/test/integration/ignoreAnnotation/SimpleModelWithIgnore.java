package org.testmonkeys.jentitytest.test.integration.ignoreAnnotation;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;

public class SimpleModelWithIgnore {

    private String name;
    private int age;

    public SimpleModelWithIgnore(String name, int age) {
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
