package org.testmonkeys.jentitytest.junit;

import org.testmonkeys.jentitytest.junit.matchers.EntityMatcher;

public final class Entity {

    public static <T> EntityMatcher<T> isEqualTo(Object expected) {
        return new EntityMatcher<>(expected);
    }
}
