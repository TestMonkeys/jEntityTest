package org.testmonkeys.jentitytest.hamcrest.matchers;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

import java.text.MessageFormat;

import static org.testmonkeys.jentitytest.Resources.comp_result;

public class DefaultResultOutput implements ResultProcessor {

    @Override
    public synchronized String getOutput(ComparisonContext context, ComparisonResult result) {
        return MessageFormat.format(Resources.getString(comp_result),
                context,result.getExpected(),result.getActual(),context.getComparatorDetails());
    }
}
