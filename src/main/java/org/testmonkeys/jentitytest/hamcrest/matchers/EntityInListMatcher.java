package org.testmonkeys.jentitytest.hamcrest.matchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparator;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.text.MessageFormat;

import static org.testmonkeys.jentitytest.Resources.desc_item_in_list;
import static org.testmonkeys.jentitytest.Resources.desc_item_not_in_list;

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
        ResultSet potentialResult = new ResultSet();

        if (!iterable.iterator().hasNext()) {
            failureReason = Resources.getString(Resources.err_actual_list_empty);
            return false;
        }

        for (T item : iterable) {
            ResultSet itemComparisonResult = compareToListItem(expected, item);
            if (itemComparisonResult.isPerfectMatch())
                return true;
            potentialResult.replaceIfBetterMatch(itemComparisonResult);
        }

        describeClosestMatch(potentialResult);
        return false;
    }

    private ResultSet compareToListItem(Object expected, T actualItem) {
        ResultSet result = new ResultSet();
        EntityComparator comparator = new EntityComparator();
        result.addAll(comparator.compare(actualItem, expected));
        return result;
    }

    private void describeClosestMatch(ResultSet closestMatch) {
        StringBuilder sb = new StringBuilder();
        for (ComparisonResult res : closestMatch.getMismatches()) {
            sb.append(resultProcessor.getOutput(res.getComparisonContext(), res));
        }
        textualOutput = sb.toString();
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
