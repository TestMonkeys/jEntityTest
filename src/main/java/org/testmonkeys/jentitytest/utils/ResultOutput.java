package org.testmonkeys.jentitytest.utils;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

public final class ResultOutput {

    public static String getOutput(ComparisonContext context, ComparisonResult result){
        StringBuilder sb = new StringBuilder();
        sb.append("Property: ").append(context).
                append("\n\tExpected: ").append(result.getExpected()).
                append("\n\tActual: ").append(result.getActual()).
                append("\nComparison was performed using ").
                append(context.getComparatorDetails()).append("\r\n");
        return sb.toString();
    }
}
