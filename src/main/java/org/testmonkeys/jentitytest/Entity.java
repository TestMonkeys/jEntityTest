package org.testmonkeys.jentitytest;

import org.testmonkeys.jentitytest.matchers.EntityMatcher;

/**
 * Created by cpascal on 6/7/2016.
 */
public class Entity {

    public static EntityMatcher isEqualTo(Object expected){
        return new EntityMatcher(expected);
    }
}
