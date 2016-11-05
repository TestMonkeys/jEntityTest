package org.testmonkeys.jentitytest.test.edge.recursiveReference;

import org.junit.Test;
import org.testmonkeys.jentitytest.Entity;

import static org.junit.Assert.assertThat;

public class RecursiveReferenceTest {

    @Test
    public void edge_recursiveReference() {
        EntityParent parent1 = new EntityParent();
        EntityChild child1 = new EntityChild();
        child1.setAge(3);
        parent1.setFirstChild(child1);
        child1.setDad(parent1);

        EntityParent parent2 = new EntityParent();
        EntityChild child2 = new EntityChild();
        child2.setAge(3);
        parent2.setFirstChild(child2);
        child2.setDad(parent2);

        assertThat(child1, Entity.isEqualTo(child2));
    }

    //@Test
    public void edge_recursiveReference2() {
        EntityParent parent1 = new EntityParent();
        EntityChild child1 = new EntityChild();
        child1.setAge(3);
        parent1.setFirstChild(child1);
        child1.setDad(parent1);

        EntityParent parent2 = new EntityParent();
        EntityChild child2 = new EntityChild();
        child2.setAge(2);
        parent2.setFirstChild(child2);
        child2.setDad(parent2);

        assertThat(child1, Entity.isEqualTo(child2));
    }
}
