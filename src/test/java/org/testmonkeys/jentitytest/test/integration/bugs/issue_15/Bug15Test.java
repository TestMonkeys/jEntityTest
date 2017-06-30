package org.testmonkeys.jentitytest.test.integration.bugs.issue_15;

import org.hamcrest.core.StringContains;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import static org.junit.Assert.assertThat;

public class Bug15Test {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void bug_15_nullChildEntity() {
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

    @Test
    public void bug_15_ChildEntity_expectedNotNull_actualNull() {
        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("mom\n\tExpected: not null\n\tActual: null"));
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
        child2.setMom(new EntityParent());
        assertThat(child1, Entity.isEqualTo(child2));
    }

    @Test
    public void bug_15_ChildEntity_expectedNull_actualNotNull() {
        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("mom\n\tExpected: null\n\tActual: not null"));

        EntityParent parent1 = new EntityParent();
        EntityChild child1 = new EntityChild();
        child1.setAge(3);
        parent1.setFirstChild(child1);
        child1.setDad(parent1);
        child1.setMom(new EntityParent());

        EntityParent parent2 = new EntityParent();
        EntityChild child2 = new EntityChild();
        child2.setAge(3);
        parent2.setFirstChild(child2);
        child2.setDad(parent2);

        assertThat(child1, Entity.isEqualTo(child2));
    }

}
