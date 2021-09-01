package org.testmonkeys.jentitytest.hamcrest.matchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparator;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import static org.testmonkeys.jentitytest.Resources.desc_entities_same;
import static org.testmonkeys.jentitytest.Resources.desc_following_props_did_not_match;
import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

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
