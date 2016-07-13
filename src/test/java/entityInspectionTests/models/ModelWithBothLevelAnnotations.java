package entityInspectionTests.models;

import org.testmonkeys.jentitytest.framework.IgnoreComparison;

public class ModelWithBothLevelAnnotations {

    @IgnoreComparisonCustom
    private int id;

    @IgnoreComparison
    public int getId() {
        return id;
    }
}
