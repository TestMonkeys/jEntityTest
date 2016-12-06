package org.testmonkeys.jentitytest.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.comparison.entity.EntityComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

import java.util.LinkedList;
import java.util.List;

public class EntityMatcher<T> extends BaseMatcher<T> {

    private final Object expected;
    private String textualOutput;
    private final char[] controlChars = {'\n', '\t', '\r', '\0', '\b', '\f'};
    private final String[] escControlChars = {"\\n", "\\t", "\\r", "\\0", "\\b", "\\f"};

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

    private StringBuilder showControlChars(Object inputObj) {
        StringBuilder res = new StringBuilder(inputObj.toString());
        for (int i = 0; i < controlChars.length; i++) {
            int index = res.indexOf(String.valueOf(controlChars[i]));
            if (index > -1) {
                res.replace(index, index + 1, escControlChars[i]);
            }
        }
        return res;
    }

}
