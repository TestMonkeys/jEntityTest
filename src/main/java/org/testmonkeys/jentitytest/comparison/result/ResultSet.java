package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;

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

    public Iterable<ComparisonResult> getMismatches() {
        return this.stream().filter(x -> x.getStatus() != Status.Passed && x.getStatus() != Status.Skipped).collect(Collectors.toList());
    }

    public void replaceIfBetterMatch(ResultSet option) {
        if (this.isEmpty() || isWorseMatchThan(option)) {
            this.clear();
            this.addAll(option);
        }
    }

    public boolean isWorseMatchThan(ResultSet option) {
        return this.isEmpty() || option.stream().filter(x -> x.getStatus() == Failed).count() < this.stream().filter(x -> x.getStatus() == Failed).count();
    }

}
