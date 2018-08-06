package org.testmonkeys.jentitytest.test.integration.childList;

import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;


public class ChildEntityListTest {

    @Test
    public void childEntityComparison() {
        CompositeModel expected = new CompositeModel();
        expected.setModelList(new ArrayList<>());
        SimpleModel expectedItem = new SimpleModel();
        expectedItem.setAge(1);
        expected.getModelList().add(expectedItem);

        CompositeModel actual = new CompositeModel();
        actual.setModelList(new ArrayList<>());
        SimpleModel actualItem = new SimpleModel();
        actualItem.setAge(1);
        actual.getModelList().add(actualItem);

        assertThat(actual, Entity.isEqualTo(expected));
    }
}
