package org.testmonkeys.jentitytest.test.unit.strategies.entityComparator;

public class SimpleModel {

    private String name;
    private int age;

    public SimpleModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
