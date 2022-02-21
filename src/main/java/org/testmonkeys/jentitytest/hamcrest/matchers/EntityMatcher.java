package org.testmonkeys.jentitytest.hamcrest.matchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparator;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;

import java.text.MessageFormat;

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
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            description.appendText(MessageFormat.format(Resources.getString(desc_entities_same), objectMapper.writeValueAsString(expected)));
        } catch (JsonProcessingException e) {
            description.appendText(MessageFormat.format(Resources.getString(desc_entities_same), expected));
        }
    }
}
