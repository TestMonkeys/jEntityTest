package org.testmonkeys.jentitytest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
     *
     */
    public void isPresentInCollection(Collection<T> collection) {
        CollectionAssertion<T> collectionAssertion = new CollectionAssertion<>(entityName,collection);
        collectionAssertion.hasItem(entity);
    }
}