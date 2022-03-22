package org.testmonkeys.jentitytest.test.integration.bugs.issue_148;

import org.hamcrest.core.StringContains;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class Issue148 {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void issue148Test(){
        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Expected: chair\n" +
                "\tActual: chain"));

        ModelWithList expected = new ModelWithList("john","car", "chair","clip","awesome","paper");
        List<ModelWithList> actualList=new ArrayList<>();
        actualList.add(new ModelWithList("john","car","chain","paper","clip","awesome"));
        assertThat(actualList, Entity.listContains(expected));
    }
}
