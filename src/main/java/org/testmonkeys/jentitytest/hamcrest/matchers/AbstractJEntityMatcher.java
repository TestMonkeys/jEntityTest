package org.testmonkeys.jentitytest.hamcrest.matchers;

import org.hamcrest.BaseMatcher;

/**
 * Created by cpascal on 6/21/2017.
 */
public abstract class AbstractJEntityMatcher<T> extends BaseMatcher<T> {
    protected ResultProcessor resultProcessor;

    protected AbstractJEntityMatcher() {
        resultProcessor = new DefaultResultOutput();
    }

    public void setResultProcessor(ResultProcessor resultProcessor) {
        this.resultProcessor = resultProcessor;
    }
}
