package org.testmonkeys.jentitytest.test.integration.regexComparison.stringComparison;

import org.testmonkeys.jentitytest.framework.RegexInExpected;

public class Model {

    @RegexInExpected
    private String field;

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
