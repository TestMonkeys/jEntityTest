package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.matchers.EntityMatcher;

public class Entity {

    public static EntityMatcher isEqualTo(Object expected) {
        return new EntityMatcher(expected);
    }
}
