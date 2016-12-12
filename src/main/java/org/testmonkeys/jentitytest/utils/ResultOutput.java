package org.testmonkeys.jentitytest.utils;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

public class ResultOutput {
    public static String getOutput(ComparisonContext context, Object expected, Object actual){
        StringBuilder sb = new StringBuilder();
        sb.append("Property: ").append(context).
                append("\n\tExpected: ").append(expected.toString()).
                append("\n\tActual: ").append(actual.toString()).
                append("\nComparison was performed using ").
                append(context.getComparatorDetails()).append("\r\n");
        return sb.toString();
    }
}
