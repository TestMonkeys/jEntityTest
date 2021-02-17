package org.testmonkeys.jentitytest.test.integration.childList;

import org.testmonkeys.jentitytest.framework.ChildEntityListComparison;

import java.util.List;

public class CompositeUnOrderedModel {

    @ChildEntityListComparison(ordered = false)
    private List<SimpleModel> modelList;

    public List<SimpleModel> getModelList() {
        return this.modelList;
    }

    public void setModelList(List<SimpleModel> modelList) {
        this.modelList = modelList;
    }
}
