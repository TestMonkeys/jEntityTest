package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.hamcrest.matchers.EntityMatcher;

/**
 * Entity is a facade for accessing matcher's
 */
public final class Entity {

    /**
     * Returns a matcher for the expected entity type
     * @param expected
     * @param <T>
     * @return
     */
    public static <T> EntityMatcher<T> isEqualTo(Object expected) {
        return new EntityMatcher<>(expected);
    }
}
