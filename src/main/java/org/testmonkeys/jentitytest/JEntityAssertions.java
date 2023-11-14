package org.testmonkeys.jentitytest;

import java.util.Collection;

/**
 * A fluent interface for chaining assertions related to entities
 */
public class JEntityAssertions {


    /**
     * Begins an assertion on an object
     *
     * @param entity The entity on which assertions will be performed.
     */
    public static synchronized <T> EntityAssertion<T> assertEntity(T entity){
        return assertEntity(null,entity);
    }

    /**
     * Begins an assertion on a named object
     *
     * @param entity     The entity on which assertions will be performed.
     * @param entityName the name of the entity
     */
    public static synchronized <T> EntityAssertion<T> assertEntity(String entityName, T entity){
        return new EntityAssertion<>(entityName, entity);
    }

    /**
     * Begins an assertion on a collection
     *
     * @param entityCollection The collection on which assertions will be performed.
     */
    public static synchronized <T> CollectionAssertion<T> assertEntityCollection(Collection<T> entityCollection){
        return assertEntityCollection(null,entityCollection);
    }

    /**
     * Begins an assertion on a named collection
     *
     * @param entityCollection The collection on which assertions will be performed.
     * @param collectionName the name of the collection
     */
    public static synchronized <T> CollectionAssertion<T> assertEntityCollection(String collectionName, Collection<T> entityCollection){
        return new CollectionAssertion<>(collectionName,entityCollection);
    }



}
