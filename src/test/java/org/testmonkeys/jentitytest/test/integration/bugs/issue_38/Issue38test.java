package org.testmonkeys.jentitytest.test.integration.bugs.issue_38;

import org.junit.Assert;
import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;
import org.testmonkeys.jentitytest.test.unit.strategies.dateTime.SimpleModel;

public class Issue38test {

    @Test
    public void issue38test(){
        SimpleModel modelExpected = new SimpleModel();
        SimpleModel modelActual = new SimpleModel();

        Assert.assertThat(modelActual, Entity.isEqualTo(modelExpected));
    }
}
