package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

import java.util.ArrayList;

/**
 * Created by cpascal on 6/15/2017.
 */
@SuppressWarnings("ReturnOfThis")
public class ResultSet extends ArrayList<ComparisonResult> {

    public ResultSet with(Status status, ComparisonContext context, Object actual, Object
            expected){
        add(new ComparisonResult(status, context, actual, expected));
        return this;
    }

    public ResultSet with(ComparisonResult result) {
        add(result);
        return this;
    }
}
