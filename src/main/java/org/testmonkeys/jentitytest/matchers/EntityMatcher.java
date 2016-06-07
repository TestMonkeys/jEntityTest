package org.testmonkeys.jentitytest.matchers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.Comparator;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Created by cpascal on 6/7/2016.
 */
public class EntityMatcher<T> extends BaseMatcher<T> {

    private Object expected;
    private EntityComparisonDictionary comparisonDictionary=EntityComparisonDictionary.getInstance();

    public EntityMatcher(Object expected){
        this.expected=expected;
    }

    @Override
    public boolean matches(Object actualItem) {
        ComparisonModel comparisonModel=comparisonDictionary.getComparisonModel(expected.getClass());
        for (PropertyDescriptor propertyDescriptor:comparisonModel.getComparableProperties()){
            Comparator comparator=comparisonModel.getComparator(propertyDescriptor);

            try {
                Object expectedValue=propertyDescriptor.getReadMethod().invoke(expected);
                Object actualValue =propertyDescriptor.getReadMethod().invoke(actualItem);
                if (!comparator.areEqual(actualValue,expectedValue))
                    return false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    @Override
    public void describeTo(Description description) {

    }
}
