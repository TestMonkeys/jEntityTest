package org.testmonkeys.jentitytest.comparison.entity;

import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.AbstractComparator;
import org.testmonkeys.jentitytest.comparison.conditionalChecks.NullConditionalCheck;
import org.testmonkeys.jentitytest.comparison.property.SimpleTypeComparator;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.comparison.result.ResultSet;
import org.testmonkeys.jentitytest.framework.JEntityTestException;

import java.util.*;

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

        Collection<?> listActual;
        Collection<?> listExpected;
        try {
            listActual = (Collection<?>) actual;
            listExpected = (Collection<?>) expected;
        } catch (ClassCastException ex) {
            throw new JEntityTestException("Actual and Expected should be Generic Collections");
        }
        if (listActual.size() != listExpected.size()) {
            comparisonResults.add(new ComparisonResult(false, context.withProperty("Size"),
                    listActual, listExpected));
            return comparisonResults;
        }
        ChildEntityComparator childComparator = new ChildEntityComparator();
        SimpleTypeComparator simpleTypeComparator = new SimpleTypeComparator();
        for (int i = 0; i < listActual.size(); i++) {
            Object actualItem = listActual.iterator().next();
            Object expectedItem = listExpected.iterator().next();
            context.setIndex(i);
            if (isWrapperType(actualItem)) {
                comparisonResults.addAll(simpleTypeComparator.compare(actualItem, expectedItem, context));
            } else {
                comparisonResults.addAll(childComparator.compare(actualItem, expectedItem, context));
            }
        }

        return comparisonResults;
    }
}
