package org.testmonkeys.jentitytest.test.stringComparison;

import org.testmonkeys.jentitytest.framework.StringComparison;

public class Model {

    @StringComparison(caseSensitive = false, ignoreCharacters = {'\n', '\r'})
    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
