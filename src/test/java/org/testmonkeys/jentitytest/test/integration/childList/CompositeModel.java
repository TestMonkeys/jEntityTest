package org.testmonkeys.jentitytest.test.integration.childList;

import org.testmonkeys.jentitytest.framework.ChildEntityListComparison;

import java.util.List;

class CompositeModel {

    @ChildEntityListComparison
    private List<SimpleModel> modelList;

    List<SimpleModel> getModelList() {
        return modelList;
    }

    void setModelList(List<SimpleModel> modelList) {
        this.modelList = modelList;
    }
}
