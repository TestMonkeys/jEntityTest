package org.testmonkeys.jentitytest.hamcrest.matchers;

import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparator;
import org.testmonkeys.jentitytest.EntityInListComparator;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.util.Collection;

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
        EntityInListComparator comparator = new EntityInListComparator();
        Collection<T> collection = getIterableList(entityList);
        if (collection==null) {
            failureReason = Resources.getString(Resources.err_actual_list_null);
            return false;
        }
        if (collection.isEmpty()) {
            failureReason = Resources.getString(Resources.err_actual_list_empty);
            return false;
        }

        ResultSet resultSet = comparator.entityInList(expected,collection);
        StringBuilder sb = new StringBuilder();
        for (ComparisonResult res : resultSet.getMismatches()) {
            sb.append(resultProcessor.getOutput(res.getComparisonContext(), res));
        }
        textualOutput = sb.toString();
        return resultSet.isPerfectMatch();
    }

    private Collection<T> getIterableList(Object entityList) {
        try {
            return (Collection<T>) entityList;
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
        description.appendText(generateDescriptionFor(desc_item_in_list, expected));
    }
}
