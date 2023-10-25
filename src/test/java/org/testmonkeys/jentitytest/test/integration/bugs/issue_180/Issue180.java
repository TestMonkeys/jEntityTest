package org.testmonkeys.jentitytest.test.integration.bugs.issue_180;

import org.hamcrest.core.StringContains;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class Issue180 {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Test
    public void testIncorrectlyUsedComparisonForLists(){
        expectedEx.expect(JEntityTestException.class);
        expectedEx.expectMessage(StringContains.containsString("comparison on lists should be done using EntityList Comparison"));

        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        assertThat(list1, Entity.isEqualTo(list2));
    }
}
