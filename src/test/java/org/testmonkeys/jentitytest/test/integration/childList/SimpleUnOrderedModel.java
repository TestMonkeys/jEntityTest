package org.testmonkeys.jentitytest.test.integration.childList;

import org.testmonkeys.jentitytest.framework.ChildEntityListComparison;

import java.util.List;

public class SimpleUnOrderedModel {
    private List<String> names;

    @ChildEntityListComparison(ordered = false)
    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }


}
