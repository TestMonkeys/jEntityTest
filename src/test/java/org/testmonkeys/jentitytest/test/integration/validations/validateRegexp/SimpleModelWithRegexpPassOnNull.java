package org.testmonkeys.jentitytest.test.integration.validations.validateRegexp;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;
import org.testmonkeys.jentitytest.framework.IgnoreComparisonIfExpectedNull;
import org.testmonkeys.jentitytest.framework.ValidateRegex;

public class SimpleModelWithRegexpPassOnNull {



    private String name;
    private int age;

    public SimpleModelWithRegexpPassOnNull(String name, int age) {
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

    @ValidateRegex(expression = "J..n", nullHandling = ValidateRegex.NullHandling.Pass)
    @IgnoreComparisonIfExpectedNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
