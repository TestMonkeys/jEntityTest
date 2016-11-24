package org.testmonkeys.jentitytest.test.unit.strategies.childList;

import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.entity.ChildEntityListComparator;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class ChildEntityListComparatorTests {

    @Test
    public void unit_ChildEntity() throws IntrospectionException {
        ChildEntityListComparator comparator = new ChildEntityListComparator();
        TestModel expected = new TestModel();
        expected.setListItem(new ArrayList<>());
        TestModel actual = new TestModel();
        actual.setListItem(new ArrayList<>());
        comparator.areEqual(new PropertyDescriptor("ListItem", TestModel.class), actual, expected, new ComparisonContext());
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
