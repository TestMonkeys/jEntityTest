package org.testmonkeys.jentitytest.hamcrest;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.hamcrest.matchers.DefaultResultOutput;
import org.testmonkeys.jentitytest.hamcrest.matchers.EntityInListMatcher;
import org.testmonkeys.jentitytest.hamcrest.matchers.EntityMatcher;
import org.testmonkeys.jentitytest.hamcrest.matchers.ResultProcessor;

/**
 * Entity is a facade for accessing matcher's
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public final class Entity {

    private static ResultProcessor resultProcessor;

    static {
        resultProcessor = new DefaultResultOutput();
    }

    public static void setResultProcessor(ResultProcessor resultProcessor) {
        if (resultProcessor == null)
            throw new IllegalArgumentException(Resources.getString(Resources.err_result_processor_null));
        Entity.resultProcessor = resultProcessor;
    }

    /**
     * Returns a matcher for the expected strategies type
     *
     * @param expected
     * @param <T>
     * @return
     */
    @SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
    public static synchronized <T> EntityMatcher<T> isEqualTo(Object expected) {
        EntityMatcher<T> matcher = new EntityMatcher<>(expected);
        matcher.setResultProcessor(resultProcessor);
        return matcher;
    }

    public static synchronized <T> EntityInListMatcher<T> listContains(Object expected) {
        EntityInListMatcher<T> matcher = new EntityInListMatcher<>(expected);
        matcher.setResultProcessor(resultProcessor);
        return matcher;
    }
}
