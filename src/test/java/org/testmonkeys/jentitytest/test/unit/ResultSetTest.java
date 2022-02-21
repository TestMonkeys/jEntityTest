package org.testmonkeys.jentitytest.test.unit;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getMismatches_none() {
        ResultSet result = new ResultSet();
        result.add(new ComparisonResult(Status.Passed, null, null, null));
        result.add(new ComparisonResult(Status.Skipped, null, null, null));
        List<ComparisonResult> comparisonResultList = new ArrayList<>();
        for (ComparisonResult item : result.getMismatches())
            comparisonResultList.add(item);
        Assert.assertEquals("mismatches", comparisonResultList.size(), 0);
    }

    @Test
    public void getMismatches_one() {
        ResultSet result = new ResultSet();
        result.add(new ComparisonResult(Status.Passed, null, null, null));
        result.add(new ComparisonResult(Status.Skipped, null, null, null));
        result.add(new ComparisonResult(Status.Failed, null, null, null));
        List<ComparisonResult> comparisonResultList = new ArrayList<>();
        for (ComparisonResult item : result.getMismatches())
            comparisonResultList.add(item);
        Assert.assertEquals("mismatches", comparisonResultList.size(), 1);
        Assert.assertEquals(comparisonResultList.get(0).getStatus(), Status.Failed);
    }
}
