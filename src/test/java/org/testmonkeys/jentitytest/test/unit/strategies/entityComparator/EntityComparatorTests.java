package org.testmonkeys.jentitytest.test.unit.strategies.entityComparator;

import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.entity.EntityComparator;

public class EntityComparatorTests {

    @Test
    public void unit_strategy_entityComparator_expectedNull(){
        EntityComparator comparator=new EntityComparator();
        comparator.compare(new SimpleModel("test",123),null);
    }
}
