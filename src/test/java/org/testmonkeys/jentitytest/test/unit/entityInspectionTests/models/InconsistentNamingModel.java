package org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;

public class InconsistentNamingModel {

    @IgnoreComparison
    private int id;

    private boolean available;

    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @IgnoreComparison
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @IgnoreComparison
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
