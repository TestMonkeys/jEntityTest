package org.testmonkeys.jentitytest.test.edge.recursiveReference;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.Entity;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
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

    @Rule
    public ExpectedException expectedException=ExpectedException.none();

    @Test
    public void edge_recursiveReference2() {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("Entity.age Expected: 2 Actual: 3");
        expectedException.expectMessage(not(containsString("Entity.dad.firstChild.age")));
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
