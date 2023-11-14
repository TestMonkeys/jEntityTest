package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import java.util.Collection;

import static org.testmonkeys.jentitytest.Resources.getStandaloneListsNotEqualAssertionMessage;

public class CollectionAssertion<T> {

    private final String entityName;
    private final Collection<T> entityCollection;
    private boolean orderedComparison=true;

    CollectionAssertion(String entityName, Collection<T> entityCollection) {
        this.entityName = entityName;
        this.entityCollection = entityCollection;
    }

    /**
     * If set will ignore the order of the elements when comparing two collections.
     */
    public CollectionAssertion<T> ignoringOrder(){
        orderedComparison=false;
        return this;
    }

    /**
     * Asserts that the collections have the same entities.
     *
     * @param expectedCollection expected collection
     */
    public void isEqualTo(Collection<T> expectedCollection) {
        EntityListComparator comparator = new EntityListComparator();

        ResultSet result = comparator.compare(entityCollection,expectedCollection,orderedComparison);

        StringBuilder sb = new StringBuilder();
        sb.append(getStandaloneListsNotEqualAssertionMessage(entityName, Entity.getResultProcessor().describeObject(expectedCollection)));

        for (ComparisonResult res : result.getMismatches()) {
            sb.append(Entity.getResultProcessor().getOutput(res.getComparisonContext(), res));
        }
        String textualOutput = sb.toString();
        if (!result.isPerfectMatch())
            throw new AssertionError(textualOutput);
    }

    /**
     * Asserts that the collection has a specific entity.
     *
     * @param item expected entity to be present in collection
     */
    public void hasItem(T item) {
        EntityInListComparator<T> comparator = new EntityInListComparator<>();

        ResultSet resultSet = comparator.entityInList(item, entityCollection);
        StringBuilder sb = new StringBuilder();
        sb.append(Resources.getStandaloneEntityNotInListAssertionMessage(entityName, Entity.getResultProcessor().describeObject(item)));

        for (ComparisonResult res : resultSet.getMismatches()) {
            sb.append(Entity.getResultProcessor().getOutput(res.getComparisonContext(), res));
        }
        if (!resultSet.isPerfectMatch())
            throw new AssertionError(sb.toString());
    }

    /**
     * Asserts that the collection has several specific entities
     *
     * @param items expected entities to be present in collection
     */
    public void hasItems(Collection<T> items) {
        EntityInListComparator<T> comparator = new EntityInListComparator<>();

        StringBuilder sb = new StringBuilder();
        boolean failed=false;
        for (T item : items) {
            ResultSet resultSet = comparator.entityInList(item, entityCollection);
            if (!resultSet.isPerfectMatch()) {
                failed=true;
                sb.append(Resources.getStandaloneEntityNotInListAssertionMessage(entityName, Entity.getResultProcessor().describeObject(item)));
                for (ComparisonResult res : resultSet.getMismatches()) {
                    sb.append(Entity.getResultProcessor().getOutput(res.getComparisonContext(), res));
                }
            }
        }
        if (failed)
            throw new AssertionError(sb.toString());
    }
}
