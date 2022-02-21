package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

import java.util.ArrayList;

public class ResultSet extends ArrayList<ComparisonResult> {

    public ResultSet with(Status status, ComparisonContext context, Object actual, Object
            expected) {
        add(new ComparisonResult(status, context, actual, expected));
        return this;
    }

    public ResultSet with(ComparisonResult result) {
        add(result);
        return this;
    }

    public boolean isPerfectMatch() {
        return this.stream().allMatch(x -> x.getStatus().equals(Status.Passed) || x.getStatus().equals(Status.Skipped));
    }

}
