package org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;

public class InconsistentNamingModel {

    private int it;

    private boolean available;

    private int age;

    public int getId() {
        return it;
    }

    public void setId(int id) {
        this.it = id;
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
