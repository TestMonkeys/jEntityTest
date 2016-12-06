package org.testmonkeys.jentitytest.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.comparison.entity.EntityComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

import java.util.LinkedList;
import java.util.List;

import static org.testmonkeys.jentitytest.utils.StringUtils.showControlChars;

public class EntityMatcher<T> extends BaseMatcher<T> {

    private final Object expected;
    private String textualOutput;


    public EntityMatcher(Object expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actualItem) {
        List<ComparisonResult> result = new LinkedList<>();
        EntityComparator comparator = new EntityComparator();
        result.addAll(comparator.compare(actualItem, expected));

        StringBuilder sb = new StringBuilder();
        for (ComparisonResult res : result) {
            if (res.isPassed())
                continue;
            sb.append("Property: ").append(res.getContext().toString()).
                    append("\n\tExpected: ").append(showControlChars(res.getExpected())).
                    append("\n\tActual: ").append(showControlChars(res.getActual()))
                    .append("\r\n");
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
