package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.matchers.EntityMatcher;

public final class Entity {

    public static <T> EntityMatcher<T> isEqualTo(Object expected) {
        return new EntityMatcher<>(expected);
    }
}
