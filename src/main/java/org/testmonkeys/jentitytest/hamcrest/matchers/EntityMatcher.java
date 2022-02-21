package org.testmonkeys.jentitytest.hamcrest.matchers;

import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparator;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;

import static org.testmonkeys.jentitytest.Resources.desc_entities_same;
import static org.testmonkeys.jentitytest.Resources.desc_following_props_did_not_match;

/**
 * Hamcrest Entity Type matcher
 *
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
     *
     * @param item actual object
     * @return true if actual object matches expected
     */
    @Override
    public boolean matches(Object item) {
        ResultSet result = new ResultSet();
        EntityComparator comparator = new EntityComparator();
        result.addAll(comparator.compare(item, expected));

        StringBuilder sb = new StringBuilder();
        for (ComparisonResult res : result.getMismatches()) {
            sb.append(resultProcessor.getOutput(res.getComparisonContext(), res));
        }
        textualOutput = sb.toString();
        return result.isPerfectMatch();
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText(Resources.getString(desc_following_props_did_not_match)).appendText(textualOutput);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(generateDescriptionFor(desc_entities_same, expected));
    }
}
