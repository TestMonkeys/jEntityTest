package org.testmonkeys.jentitytest.test.unit.model;

import org.junit.Test;
import org.testmonkeys.jentitytest.model.ComparisonModel;
import org.testmonkeys.jentitytest.model.EntityToComparisonModelDictionary;

import java.awt.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EntityToComparisonModelDictionaryTest {

    private final EntityToComparisonModelDictionary dictionary = EntityToComparisonModelDictionary.getInstance();

    @Test
    public void unit_comparisonDictionary_registeringComparisonModel() {
        ComparisonModel model = new ComparisonModel();
        this.dictionary.addComparisonModel(Point.class, model);
        ComparisonModel registered = this.dictionary.getComparisonModel(Point.class);
        assertThat(registered, is(model));
    }
}
