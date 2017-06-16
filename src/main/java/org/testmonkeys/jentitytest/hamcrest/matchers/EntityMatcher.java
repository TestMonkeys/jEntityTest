package org.testmonkeys.jentitytest.hamcrest.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.utils.ResultOutput;

import java.util.LinkedList;
import java.util.List;

/**
 * Hamcrest Entity Type matcher for type <T>
 * @param <T> type of entity to be matched
 */
public class EntityMatcher<T> extends BaseMatcher<T> {

    private final Object expected;
    private String textualOutput;


    public EntityMatcher(Object expected) {
        this.expected = expected;
    }

    /**
     * Performs matching using the EntityComparator between the actualItem and expected
     * @param actualItem
     * @return
     */
    @Override
    public boolean matches(Object actualItem) {
        List<ComparisonResult> result = new LinkedList<>();
        EntityComparator comparator = new EntityComparator();
        result.addAll(comparator.compare(actualItem, expected));

        StringBuilder sb = new StringBuilder();
        for (ComparisonResult res : result) {
            if (res.isPassed())
                continue;
            sb.append(ResultOutput.getOutput(res.getComparisonContext(), res));
        }
        textualOutput = sb.toString();
        return result.stream().allMatch(ComparisonResult::isPassed);
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText("Following properties didn't match:\r\n").appendText(textualOutput);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Entities have same values in properties");
    }
}
