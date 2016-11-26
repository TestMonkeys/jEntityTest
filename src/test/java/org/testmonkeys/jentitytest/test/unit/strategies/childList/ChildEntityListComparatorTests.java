package org.testmonkeys.jentitytest.test.unit.strategies.childList;

import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.entity.ChildEntityListComparator;
import org.testmonkeys.jentitytest.matchers.ObjectPropertyComparator;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

public class ChildEntityListComparatorTests {

    @Test
    public void unit_ChildEntity() throws IntrospectionException {
        ObjectPropertyComparator comparator = new ObjectPropertyComparator(new ChildEntityListComparator());
        TestModel expected = new TestModel();
        expected.setListItem(new ArrayList<>());
        TestModel actual = new TestModel();
        actual.setListItem(new ArrayList<>());
        //comparator.areEqual( actual, expected, new ComparisonContext());
    }

    public class TestModel {

        private List<String> listItem;


        public List<String> getListItem() {
            return listItem;
        }

        public void setListItem(List<String> listItem) {
            this.listItem = listItem;
        }
    }
}
