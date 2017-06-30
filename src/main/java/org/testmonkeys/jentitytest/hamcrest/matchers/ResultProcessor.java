package org.testmonkeys.jentitytest.hamcrest.matchers;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

/**
 * Created by cpascal on 6/16/2017.
 */
public interface ResultProcessor {
    String getOutput(ComparisonContext context, ComparisonResult result);
}
