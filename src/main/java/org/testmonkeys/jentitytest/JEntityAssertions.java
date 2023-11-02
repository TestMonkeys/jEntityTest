package org.testmonkeys.jentitytest;

import java.util.Collection;

public class JEntityAssertions<T> {

    public static synchronized <T> EntityAssertion<T> assertEntity(T entity){
        return assertEntity(null,entity);
    }

    public static synchronized <T> EntityAssertion<T> assertEntity(String entityName, T entity){
        return new EntityAssertion<T>(entityName,entity);
    }

    public static synchronized <T> CollectionAssertion<T> assertEntityCollection(Collection<T> entityCollection){
        return assertEntityCollection(null,entityCollection);
    }

    public static synchronized <T> CollectionAssertion<T> assertEntityCollection(String collectionName, Collection<T> entityCollection){
        return new CollectionAssertion<>(collectionName,entityCollection);
    }



}
