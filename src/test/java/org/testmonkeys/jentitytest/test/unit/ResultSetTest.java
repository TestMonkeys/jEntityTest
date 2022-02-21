package org.testmonkeys.jentitytest.test.unit;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;

public class ResultSetTest {

    @Test
    public void perfectMatch_true() {
        ResultSet result = new ResultSet();
        result.add(new ComparisonResult(Status.Passed, null, null, null));
        result.add(new ComparisonResult(Status.Skipped, null, null, null));
        Assert.assertTrue("perfect match", result.isPerfectMatch());
    }

    @Test
    public void perfectMatch_false() {
        ResultSet result = new ResultSet();
        result.add(new ComparisonResult(Status.Passed, null, null, null));
        result.add(new ComparisonResult(Status.Skipped, null, null, null));
        result.add(new ComparisonResult(Status.Failed, null, null, null));
        Assert.assertFalse("perfect match", result.isPerfectMatch());
    }
}
