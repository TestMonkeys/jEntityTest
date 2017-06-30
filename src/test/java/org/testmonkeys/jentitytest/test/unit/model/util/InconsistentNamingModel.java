package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;

public class InconsistentNamingModel {

    private int it;

    private boolean available;

    private int age;

    public int getId() {
        return this.it;
    }

    public void setId(int id) {
        it = id;
    }

    @IgnoreComparison
    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @IgnoreComparison
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
