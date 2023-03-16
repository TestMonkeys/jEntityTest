package org.testmonkeys.jentitytest.hamcrest;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.hamcrest.matchers.DefaultResultOutput;
import org.testmonkeys.jentitytest.hamcrest.matchers.EntityInListMatcher;
import org.testmonkeys.jentitytest.hamcrest.matchers.EntityListMatcher;
import org.testmonkeys.jentitytest.hamcrest.matchers.EntityMatcher;
import org.testmonkeys.jentitytest.hamcrest.matchers.ResultProcessor;

/**
 * EntityList is a facade for accessing matcher's related to lists
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public final class EntityList {

    private static ResultProcessor resultProcessor;

    static {
        resultProcessor = new DefaultResultOutput();
    }

    public static void setResultProcessor(ResultProcessor resultProcessor) {
        if (resultProcessor == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_result_processor_null));
        EntityList.resultProcessor = resultProcessor;
    }

    /**
     * Returns a matcher for the comparing two lists
     *
     * @param expected expected object list
     * @param <T>      entity type
     * @return entity matcher
     */
    @SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
    public static synchronized <T> EntityListMatcher<T> isEqualTo(Object expected) {
        EntityListMatcher<T> matcher = new EntityListMatcher<>(expected);
        matcher.setResultProcessor(resultProcessor);
        return matcher;
    }

    /**
     * Returns a matcher for matching if an item is in a list
     *
     * @param expected expected object
     * @param <T>      entity type
     * @return entity matcher
     */
    public static synchronized <T> EntityInListMatcher<T> contains(Object expected) {
        EntityInListMatcher<T> matcher = new EntityInListMatcher<>(expected);
        matcher.setResultProcessor(resultProcessor);
        return matcher;
    }
}
