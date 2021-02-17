package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.comparison.result.Status;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.ChildEntityListComparison;

import java.util.*;

import static org.testmonkeys.jentitytest.Resources.err_actual_and_expected_must_be_generic_Collections;
import static org.testmonkeys.jentitytest.Resources.size;
import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;

public class ChildEntityListComparator extends AbstractComparator {

    private boolean ordered = true;

    public ChildEntityListComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
    }

    public ChildEntityListComparator(ChildEntityListComparison annotation) {
        registerPreConditionalCheck(new NullConditionalCheck());
        ordered = annotation.ordered();
    }

    private static boolean isWrapperType(Object obj) {
        return getWrapperTypes().contains(obj.getClass());
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        ret.add(String.class);
        return ret;
    }

    /**
     * @param actual
     * @param expected
     * @param context
     * @return
     * @throws JEntityTestException
     */
    @Override
    protected ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        ResultSet comparisonResults = new ResultSet();

        Collection<?> listActual = castToCollection(actual);
        Collection<?> listExpected = castToCollection(expected);

        if (listActual.size() != listExpected.size()) {
            comparisonResults.add(new ComparisonResult(Failed, context.withProperty(Resources.getString(size)),
                    listActual.size(), listExpected.size()));
            return comparisonResults;
        }

        Comparator elementComparator;
        if (!listExpected.isEmpty() && isWrapperType(listExpected.iterator().next()))
            elementComparator = new SimpleTypeComparator();
        else
            elementComparator = new ChildEntityComparator();

        if (ordered)
            return computeOrderedComparison(listActual, listExpected, context, elementComparator);
        else
            return computeUnOrderedComparison(listActual, listExpected, context, elementComparator);
    }

    private ResultSet computeOrderedComparison(Collection<?> listActual, Collection<?> listExpected, ComparisonContext context, Comparator elementComparator) {
        ResultSet comparisonResults = new ResultSet();
        Iterator<?> actualIterator = listActual.iterator();
        Iterator<?> expectedIterator = listExpected.iterator();
        for (int i = 0; i < listActual.size(); i++) {
            comparisonResults.addAll(elementComparator.compare(
                    actualIterator.next(),
                    expectedIterator.next(),
                    context.withIndex(i)));
        }

        return comparisonResults;
    }

    private ResultSet computeUnOrderedComparison(Collection<?> listActual, Collection<?> listExpected, ComparisonContext context, Comparator elementComparator) {
        ResultSet comparisonResults = new ResultSet();

        Object[] actualArr = listActual.toArray();
        Object[] expectedArr = listExpected.toArray();

        for (int i = 0; i < listExpected.size(); i++) {
            List<ResultSet> options = new ArrayList<>();
            for (int j = 0; j < actualArr.length; j++) {
                ResultSet option = elementComparator.compare(
                        actualArr[j],
                        expectedArr[i],
                        context.withIndex(i));
                options.add(option);
                if (option.stream().allMatch(x -> x.getStatus().equals(Status.Passed) || x.getStatus().equals(Status.Skipped)))
                    break;
            }
            ResultSet closestOption = options.get(0);
            for (ResultSet option : options) {
                if (option.stream().filter(x -> x.getStatus().equals(Failed)).count() < closestOption.stream().filter(x -> x.getStatus().equals(Failed)).count())
                    closestOption = option;
            }
            comparisonResults.addAll(closestOption);
        }

        return comparisonResults;
    }

    private Collection<?> castToCollection(Object object) {
        try {
            return (Collection<?>) object;
        } catch (ClassCastException ex) {
            throw new JEntityTestException(Resources.getString(err_actual_and_expected_must_be_generic_Collections), ex);
        }
    }
}
