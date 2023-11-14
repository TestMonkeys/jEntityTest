package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import java.util.Collection;

public class EntityAssertion<T> {
    final String entityName;
    final T entity;
    EntityAssertion(String entityName, T entity){

        this.entityName = entityName;
        this.entity = entity;
    }

    /**
     * Asserts that the entities are the same
     *
     * @param expected expected entity
     */
    public void isEqualTo(T expected) {
        EntityComparator comparator = new EntityComparator();
        ResultSet result = comparator.compare(entity, expected);

        StringBuilder sb = new StringBuilder();
        sb.append(Resources.getStandaloneEntitiesNotEqualAssertionMessage(entityName, Entity.getResultProcessor().describeObject(expected)));

        for (ComparisonResult res : result.getMismatches()) {
            sb.append(Entity.getResultProcessor().getOutput(res.getComparisonContext(), res));
        }
        String textualOutput = sb.toString();
        if (!result.isPerfectMatch())
            throw new AssertionError(textualOutput);
    }


    /**
     * Asserts that the entity is present in the collection
     *
     * @param collection the collection in which to look for the entity
     */
    public void isPresentInCollection(Collection<T> collection) {
        CollectionAssertion<T> collectionAssertion = new CollectionAssertion<>(entityName,collection);
        collectionAssertion.hasItem(entity);
    }
}