package org.testmonkeys.jentitytest.comparison.result;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;

import java.util.ArrayList;

/**
 * Created by cpascal on 6/15/2017.
 */
public class ResultSet extends ArrayList<ComparisonResult> {

    public ResultSet(boolean passed, ComparisonContext context, Object actual, Object
            expected) {
        add(new ComparisonResult(passed,context,actual,expected));
    }

    public ResultSet(){

    }

    public void add(boolean passed, ComparisonContext context, Object actual, Object
            expected){
        add(new ComparisonResult(passed,context,actual,expected));
    }
}
