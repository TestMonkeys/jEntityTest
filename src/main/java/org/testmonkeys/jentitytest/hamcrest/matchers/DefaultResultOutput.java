package org.testmonkeys.jentitytest.hamcrest.matchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

import java.text.MessageFormat;

import static org.testmonkeys.jentitytest.Resources.comp_result;
import static org.testmonkeys.jentitytest.Resources.comp_result_light;

public class DefaultResultOutput implements ResultProcessor {

    @Override
    public synchronized String getOutput(ComparisonContext context, ComparisonResult result) {
        if (context.getComparatorDetails() == null || context.getComparatorDetails().equalsIgnoreCase("SimpleTypeComparator"))
            return MessageFormat.format(Resources.getString(comp_result_light), context, result.getExpected(), result.getActual());
        else
            return MessageFormat.format(Resources.getString(comp_result),
                context, result.getExpected(), result.getActual(), context.getComparatorDetails());
    }

    @Override
    public synchronized String describeObject(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return o.toString();
        }
    }

}
