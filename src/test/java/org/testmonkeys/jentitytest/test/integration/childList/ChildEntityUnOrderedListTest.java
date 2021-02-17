package org.testmonkeys.jentitytest.test.integration.childList;

import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;


public class ChildEntityUnOrderedListTest {

    @Test
    public void childEntityComparison() {
        CompositeUnOrderedModel expected = new CompositeUnOrderedModel();
        expected.setModelList(new ArrayList<>());
        SimpleModel expectedItem = new SimpleModel();
        expectedItem.setAge(1);
        expected.getModelList().add(expectedItem);

        CompositeUnOrderedModel actual = new CompositeUnOrderedModel();
        actual.setModelList(new ArrayList<>());
        SimpleModel actualItem = new SimpleModel();
        actualItem.setAge(1);
        actual.getModelList().add(actualItem);

        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void childEntityListComparisonUnOrdered() {
        CompositeUnOrderedModel expected = new CompositeUnOrderedModel();
        expected.setModelList(new ArrayList<>());
        SimpleModel expectedItem1 = new SimpleModel();
        expectedItem1.setAge(1);
        SimpleModel expectedItem2 = new SimpleModel();
        expectedItem2.setAge(2);
        expected.getModelList().add(expectedItem1);
        expected.getModelList().add(expectedItem2);

        CompositeUnOrderedModel actual = new CompositeUnOrderedModel();
        actual.setModelList(new ArrayList<>());
        SimpleModel actualItem1 = new SimpleModel();
        actualItem1.setAge(1);
        SimpleModel actualItem2 = new SimpleModel();
        actualItem2.setAge(2);
        actual.getModelList().add(actualItem2);
        actual.getModelList().add(actualItem1);

        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test
    public void childEntityValueListComparisonUnOrdered() {
        SimpleUnOrderedModel expected = new SimpleUnOrderedModel();
        expected.setNames(new ArrayList<>());
        expected.getNames().add("Name1");
        expected.getNames().add("Name2");
        expected.getNames().add("Name3");

        SimpleUnOrderedModel actual = new SimpleUnOrderedModel();
        actual.setNames(new ArrayList<>());
        actual.getNames().add("Name2");
        actual.getNames().add("Name3");
        actual.getNames().add("Name1");

        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void childEntityListComparisonUnOrdered_failing() {
        CompositeUnOrderedModel expected = new CompositeUnOrderedModel();
        expected.setModelList(new ArrayList<>());
        SimpleModel expectedItem1 = new SimpleModel();
        expectedItem1.setAge(1);
        SimpleModel expectedItem2 = new SimpleModel();
        expectedItem2.setAge(2);
        expected.getModelList().add(expectedItem1);
        expected.getModelList().add(expectedItem2);

        CompositeUnOrderedModel actual = new CompositeUnOrderedModel();
        actual.setModelList(new ArrayList<>());
        SimpleModel actualItem1 = new SimpleModel();
        actualItem1.setAge(1);
        SimpleModel actualItem2 = new SimpleModel();
        actualItem2.setAge(3);
        actual.getModelList().add(actualItem2);
        actual.getModelList().add(actualItem1);

        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void childEntityValueListComparisonUnOrdered_failing() {
        SimpleUnOrderedModel expected = new SimpleUnOrderedModel();
        expected.setNames(new ArrayList<>());
        expected.getNames().add("Name1");
        expected.getNames().add("Name2");
        expected.getNames().add("Name3");

        SimpleUnOrderedModel actual = new SimpleUnOrderedModel();
        actual.setNames(new ArrayList<>());
        actual.getNames().add("Name2");
        actual.getNames().add("Name3");
        actual.getNames().add("Name4");

        assertThat(actual, Entity.isEqualTo(expected));
    }
}
