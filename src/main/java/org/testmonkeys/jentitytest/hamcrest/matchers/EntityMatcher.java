package org.testmonkeys.jentitytest.hamcrest.matchers;

import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

import java.util.LinkedList;
import java.util.List;

import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

/**
 * Hamcrest Entity Type matcher for type <T>
 * @param <T> type of strategies to be matched
 */
public class EntityMatcher<T> extends AbstractJEntityMatcher<T> {

    private final Object expected;
    private String textualOutput;

    public EntityMatcher(Object expected) {
        this.expected = expected;
    }

    /**
     * Performs matching using the EntityComparator between the actualItem and expected
     * @param item
     * @return
     */
    @Override
    public boolean matches(Object item) {
        List<ComparisonResult> result = new LinkedList<>();
        EntityComparator comparator = new EntityComparator();
        result.addAll(comparator.compare(item, expected));

        StringBuilder sb = new StringBuilder();
        for (ComparisonResult res : result) {
            if (res.getStatus() == Passed)
                continue;
            sb.append(resultProcessor.getOutput(res.getComparisonContext(), res));
        }
        textualOutput = sb.toString();
        return result.stream().allMatch(r -> r.getStatus() == Passed);
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
