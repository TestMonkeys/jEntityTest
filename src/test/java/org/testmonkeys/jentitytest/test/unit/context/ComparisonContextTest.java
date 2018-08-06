package org.testmonkeys.jentitytest.test.unit.context;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;

/**
 * Created by cpascal on 6/30/2017.
 */
public class ComparisonContextTest {

    @Test
    public void positiveIndex() {
        ComparisonContext context = new ComparisonContext();
        context.setIndex(0);
        Assert.assertThat(context.toString(), StringContains.containsString("[0]"));
    }
}
