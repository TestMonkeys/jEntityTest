package org.testmonkeys.jentitytest.test.unit.strategies.childList;

import org.hamcrest.junit.ExpectedException;
import org.junit.Rule;
import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.strategies.ChildEntityListComparator;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;
import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

public class ChildEntityListComparatorTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void defaultOrderedParameter() {
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        assertTrue("ordered", comparator.isOrdered());
    }

    @Test
    public void orderedParameterProperty() {
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        comparator.setOrdered(false);
        assertFalse("ordered", comparator.isOrdered());
    }

    @Test
    public void actualNotCollection() {
        expectedException.expect(JEntityTestException.class);
        expectedException.expectMessage("Actual and Expected should be Generic Collections");
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        comparator.compare("a string", new ArrayList<String>(), new ComparisonContext());
    }

    @Test
    public void expectedNotCollection() {
        expectedException.expect(JEntityTestException.class);
        expectedException.expectMessage("Actual and Expected should be Generic Collections");
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        comparator.compare(new ArrayList<String>(), "a string", new ComparisonContext());
    }

    @Test
    public void emptyCollections() {
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        List<ComparisonResult> results = comparator.compare(new ArrayList<String>(), new ArrayList<String>(), new ComparisonContext());

        assertTrue(results.stream().allMatch(r -> r.getStatus() == Passed));
    }

    @Test
    public void stringCollections_ValueMismatch() {
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        List<String> actual = new ArrayList<>();
        List<String> expected = new ArrayList<>();
        actual.add("Line1Actual");
        expected.add("Line1Expected");

        List<ComparisonResult> results = comparator.compare(actual, expected, new ComparisonContext());

        assertTrue(results.stream().anyMatch(res -> res.getStatus() == Failed));
    }

    @Test
    public void stringCollections_CountMismatch() {
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        List<String> actual = new ArrayList<>();
        List<String> expected = new ArrayList<>();
        actual.add("Line1Actual");
        actual.add("Line2Actual");
        expected.add("Line1Actual");

        List<ComparisonResult> results = comparator.compare(actual, expected, new ComparisonContext());

        assertTrue(results.stream().anyMatch(res -> res.getStatus() == Failed));
    }


    @Test
    public void stringCollections_Match() {
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        List<String> actual = new ArrayList<>();
        List<String> expected = new ArrayList<>();
        actual.add("Line1Actual");
        actual.add("Line2Actual");
        expected.add("Line1Actual");
        expected.add("Line2Actual");

        List<ComparisonResult> results = comparator.compare(actual, expected, new ComparisonContext());

        assertTrue(results.stream().allMatch(r -> r.getStatus() == Passed));
    }

    @Test
    public void entityCollections_Match() {
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        List<SimpleModel> actual = new ArrayList<>();
        List<SimpleModel> expected = new ArrayList<>();
        actual.add(new SimpleModel(1));
        actual.add(new SimpleModel(1));
        expected.add(new SimpleModel(1));
        expected.add(new SimpleModel(1));

        List<ComparisonResult> results = comparator.compare(actual, expected, new ComparisonContext());

        assertTrue(results.stream().allMatch(r -> r.getStatus() == Passed));
    }

}
