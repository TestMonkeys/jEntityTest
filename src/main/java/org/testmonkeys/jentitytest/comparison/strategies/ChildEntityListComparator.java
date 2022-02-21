package org.testmonkeys.jentitytest.comparison.strategies;

import lombok.Getter;
import lombok.Setter;
import org.testmonkeys.jentitytest.Resources;
import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.framework.ChildEntityListComparison;

import java.util.*;

import static org.testmonkeys.jentitytest.Resources.size;
import static org.testmonkeys.jentitytest.comparison.result.Status.Failed;

public class ChildEntityListComparator extends AbstractComparator {

    @Getter
    @Setter
    private boolean ordered = true;

    private final TypeCastingUtils typeCasting = new TypeCastingUtils();

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
     * @param actual   actual object
     * @param expected expected object
     * @param context  context of this comparison
     * @return result set of the comparison
     * @throws JEntityTestException if comparison is impossible
     */
    @Override
    protected ResultSet computeComparison(Object actual, Object expected, ComparisonContext context) {
        ResultSet comparisonResults = new ResultSet();

        Collection<?> listActual = typeCasting.toCollection(actual);
        Collection<?> listExpected = typeCasting.toCollection(expected);

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
            List<ResultSet> options = findMatches(expectedArr[i], actualArr, elementComparator, context.withIndex(i));
            comparisonResults.addAll(findClosestOption(options));
        }

        return comparisonResults;
    }

    private List<ResultSet> findMatches(Object expected, Object[] actualArr, Comparator elementComparator, ComparisonContext context) {
        List<ResultSet> options = new ArrayList<>();
        for (Object actualObject : actualArr) {
            ResultSet option = elementComparator.compare(
                    actualObject,
                    expected,
                    context);
            options.add(option);
            if (option.isPerfectMatch())
                break;
        }
        return options;
    }

    private ResultSet findClosestOption(List<ResultSet> options) {
        ResultSet closestOption = options.get(0);
        for (ResultSet option : options) {
            if (option.stream().filter(x -> x.getStatus().equals(Failed)).count() < closestOption.stream().filter(x -> x.getStatus().equals(Failed)).count())
                closestOption = option;
        }
        return closestOption;
    }


}
