package org.testmonkeys.jentitytest.hamcrest.matchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;


public interface ResultProcessor {
    String getOutput(ComparisonContext context, ComparisonResult result);
    String describeObject(Object o);
}
