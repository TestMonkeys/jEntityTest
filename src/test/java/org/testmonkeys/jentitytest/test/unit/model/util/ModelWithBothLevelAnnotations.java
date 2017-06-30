package org.testmonkeys.jentitytest.test.unit.model.util;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;

public class ModelWithBothLevelAnnotations {

    @IgnoreComparisonCustom
    private int id;

    @IgnoreComparison
    public int getId() {
        return this.id;
    }
}
