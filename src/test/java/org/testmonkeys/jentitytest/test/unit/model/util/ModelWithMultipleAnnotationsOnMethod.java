package org.testmonkeys.jentitytest.test.unit.model.util;


import org.testmonkeys.jentitytest.framework.DateTimeComparison;
import org.testmonkeys.jentitytest.framework.StringComparison;

public class ModelWithMultipleAnnotationsOnMethod {

    @StringComparison
    @DateTimeComparison
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
