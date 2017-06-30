package org.testmonkeys.jentitytest.comparison.strategies;

import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ChildEntityListComparator extends AbstractComparator {

    public ChildEntityListComparator() {
        registerPreConditionalCheck(new NullConditionalCheck());
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
            comparisonResults.add(new ComparisonResult(false, context.withProperty("Size"),
                    listActual.size(), listExpected.size()));
            return comparisonResults;
        }

        Comparator elementComparator;
        if (!listExpected.isEmpty() && isWrapperType(listExpected.iterator().next()))
            elementComparator = new SimpleTypeComparator();
        else
            elementComparator = new ChildEntityComparator();

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

    private Collection<?> castToCollection(Object object) {
        try {
            return (Collection<?>) object;
        } catch (ClassCastException ex) {
            throw new JEntityTestException("Actual and Expected should be Generic Collections",ex);
        }
    }
}
