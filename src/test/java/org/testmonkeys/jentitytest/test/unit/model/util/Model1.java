package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;

public class Model1 {

    @IgnoreComparison
    private int id;

    private boolean available;

    private int age;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
