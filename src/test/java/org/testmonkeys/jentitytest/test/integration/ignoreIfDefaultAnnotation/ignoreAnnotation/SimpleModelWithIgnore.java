package org.testmonkeys.jentitytest.test.integration.ignoreIfDefaultAnnotation.ignoreAnnotation;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;
import org.testmonkeys.jentitytest.framework.IgnoreComparisonIfExpectedDefault;

public class SimpleModelWithIgnore {
    @IgnoreComparisonIfExpectedDefault
    private String name;
    private int age;

    public SimpleModelWithIgnore(String name, int age) {
        this.name = name;
        this.age = age;
    }


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
