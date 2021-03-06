package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;

/**
 * Model used for testing how jEntityTest will do inspection when there are annotations
 * from other frameworks.
 */
public class ModelMultiAnnotated {

    @SimpleAnnotation
    @IgnoreComparison
    private int id;

    @SimpleAnnotation
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

    @SimpleAnnotation
    @IgnoreComparison
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
