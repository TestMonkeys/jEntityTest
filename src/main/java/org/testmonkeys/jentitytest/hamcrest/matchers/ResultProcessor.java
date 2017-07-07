package org.testmonkeys.jentitytest.hamcrest.matchers;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;


public interface ResultProcessor {
    String getOutput(ComparisonContext context, ComparisonResult result);
}
