package org.testmonkeys.jentitytest.hamcrest.matchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparator;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import static org.testmonkeys.jentitytest.Resources.desc_item_in_list;
import static org.testmonkeys.jentitytest.Resources.desc_item_not_in_list;
import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;
import static org.testmonkeys.jentitytest.comparison.result.Status.Passed;

/**
 * Hamcrest matcher for finding an entity in a list
 *
 * @param <T> type of strategies to be matched
 */
public class EntityInListMatcher<T> extends AbstractJEntityMatcher<T> {

    private final Object expected;
    private String textualOutput;
    private String failureReason = Resources.getString(desc_item_not_in_list);

    public EntityInListMatcher(Object expected) {
        this.expected = expected;
    }

    /**
     * Performs iterative matching using the EntityComparator between the actualItem and entityList items
     *
     * @param entityList actual list
     * @return true if expected item is contained in list
     */
    @Override
    public boolean matches(Object entityList) {
        Iterable<T> iterable = getIterableList(entityList);
        List<ComparisonResult> closestMatch = null;

        boolean anyItems = false;
        for (T item : iterable) {
            anyItems = true;

            List<ComparisonResult> result = new LinkedList<>();
            EntityComparator comparator = new EntityComparator();
            result.addAll(comparator.compare(item, expected));

            if (result.stream().allMatch(x -> x.getStatus() == Passed))
                return true;

            if (closestMatch == null || (result.stream().filter(x -> x.getStatus() == Failed).count() < closestMatch.stream().filter(x -> x.getStatus() == Failed).count())) {
                closestMatch = result;
            }


        }
        StringBuilder sb = new StringBuilder();
        if (anyItems) {
            for (ComparisonResult res : closestMatch) {
                if (res.getStatus() == Passed)
                    continue;
                sb.append(resultProcessor.getOutput(res.getComparisonContext(), res));
            }
        } else {
            failureReason = Resources.getString(Resources.err_actual_list_empty);
        }
        textualOutput = sb.toString();
        return false;
    }

    private Iterable<T> getIterableList(Object entityList) {
        try {
            return (Iterable<T>) entityList;
        } catch (Exception e) {
            throw new JEntityTestException(Resources.getString(Resources.err_actual_not_iterable), e);
        }
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText(failureReason).appendText(textualOutput);
    }

    @Override
    public void describeTo(Description description) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            description.appendText(MessageFormat.format(Resources.getString(desc_item_in_list), objectMapper.writeValueAsString(expected)));
        } catch (JsonProcessingException e) {
            description.appendText(MessageFormat.format(Resources.getString(desc_item_in_list), expected));
        }
    }
}
