package org.testmonkeys.jentitytest.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.entity.EntityComparator;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.util.LinkedList;
import java.util.List;

public class EntityMatcher<T> extends BaseMatcher<T> {

    private final Object expected;
    private final EntityComparisonDictionary comparisonDictionary = EntityComparisonDictionary.getInstance();
    private String textualOutput;

    public EntityMatcher(Object expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actualItem) {
        ComparisonModel comparisonModel = null;
        //TODO: think abouth the best way of throwing the exception
        try {
            comparisonModel = comparisonDictionary.getComparisonModel(expected.getClass());
        } catch (JEntityTestException e) {
            e.printStackTrace();
        }
        List<ComparisonResult> result=new LinkedList<>();
        EntityComparator comparator = new EntityComparator();
        try {
            result.addAll(comparator.compare(actualItem,expected,comparisonModel));
        } catch (JEntityTestException e) {
            e.printStackTrace();
        }
        StringBuilder sb=new StringBuilder();
        for ( ComparisonResult res:result)
        {
            if (res.isPassed())
                continue;
            sb.append(res.getContext().toString()).
                    append(" Expected: ").append(res.getExpected()).
                    append(" Actual: ").append(res.getActual()).append("" +
                    "\r\n");
        }
        textualOutput=sb.toString();
        return result.stream().allMatch(p -> p.isPassed());
    }

    @Override
    public void describeMismatch(Object item, Description description) {

        description.appendText("Follwoing properties didn't match:\r\n").appendText(textualOutput);

    }

    @Override
    public void describeTo(Description description) {

       description.appendText("Entities have same values in properties");
        //description.appendText("textValue");//description.appendText(textualOutput);
    }

    @Override
    public String toString(){
        return "test";
    }
}
