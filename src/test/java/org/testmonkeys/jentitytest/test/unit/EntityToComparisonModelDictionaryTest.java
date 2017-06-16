package org.testmonkeys.jentitytest.test.unit;

import org.junit.Test;
import org.testmonkeys.jentitytest.EntityComparisonDictionary;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;

import java.awt.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EntityComparisonDictionaryTest {

    private final EntityComparisonDictionary dictionary = EntityComparisonDictionary.getInstance();

    @Test
    public void unit_comparisonDictionary_registeringComparisonModel() {
        ComparisonModel model = new ComparisonModel();
        dictionary.addComparisonModel(Point.class, model);
        ComparisonModel registered = dictionary.getComparisonModel(Point.class);
        assertThat(registered, is(model));
    }
}
