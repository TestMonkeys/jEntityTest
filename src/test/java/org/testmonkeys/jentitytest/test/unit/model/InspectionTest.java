package org.testmonkeys.jentitytest.test.unit.model;

import org.hamcrest.junit.ExpectedException;
import org.junit.Rule;
import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.strategies.IgnoreComparator;
import org.testmonkeys.jentitytest.comparison.strategies.SimpleTypeComparator;
import org.testmonkeys.jentitytest.exceptions.JEntityModelException;
import org.testmonkeys.jentitytest.model.AnnotationToComparatorDictionary;
import org.testmonkeys.jentitytest.model.ComparisonModel;
import org.testmonkeys.jentitytest.model.EntityInspector;
import org.testmonkeys.jentitytest.test.unit.model.util.*;

import java.beans.PropertyDescriptor;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InspectionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Checks {@link EntityInspector} that it will not try to understand getter methods starting with has
     *
     * @throws Throwable
     */
    @Test
    public void unit_inspection_NoHasGetters() throws Throwable {
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(InvalidProperties.class);
        Set<PropertyDescriptor> props = model.getComparableProperties();
        assertThat("hasProp in model", props.stream().anyMatch(x -> x.getReadMethod().equals("hasMarshmallows")), is(false));
    }

    @Test
    public void unit_inspection_FieldLevelAnnotation() throws Throwable {
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(Model1.class);
        Set<PropertyDescriptor> props = model.getComparableProperties();
        assertThat("'id' in model", props.stream().anyMatch(x -> x.getName().equals("id")), is(true));
        PropertyDescriptor prop = props.stream().filter(x -> x.getName().equals("id")).findFirst().get();
        assertThat("comparator", model.getComparator(prop).getComparator().getClass(), is(IgnoreComparator.class));
    }

    @Test
    public void unit_inspection_IsGetterLevelAnnotation() throws Throwable {
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(Model1.class);
        Set<PropertyDescriptor> props = model.getComparableProperties();
        assertThat("'available' in model", props.stream().anyMatch(x -> x.getName().equals("available")), is(true));
        PropertyDescriptor prop = props.stream().filter(x -> x.getName().equals("available")).findFirst().get();
        assertThat("comparator", model.getComparator(prop).getComparator().getClass(), is(IgnoreComparator.class));
    }

    @Test
    public void unit_inspection_SimpleGetterLevelAnnotation() throws Throwable {
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(Model1.class);
        Set<PropertyDescriptor> props = model.getComparableProperties();
        assertThat("'age' in model", props.stream().anyMatch(x -> x.getName().equals("age")), is(true));
        PropertyDescriptor prop = props.stream().filter(x -> x.getName().equals("age")).findFirst().get();
        assertThat("comparator", model.getComparator(prop).getComparator().getClass(), is(IgnoreComparator.class));
    }

    @Test
    public void unit_inspection_TakesFiledAnnotationInsteadOfGetter() throws Throwable {
        AnnotationToComparatorDictionary.getInstance().setComparatorForAnnotation(IgnoreComparatorCustom.class, IgnoreComparisonCustom.class);
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(ModelWithBothLevelAnnotations.class);
        Set<PropertyDescriptor> props = model.getComparableProperties();
        assertThat("'id' in model", props.stream().anyMatch(x -> x.getName().equals("id")), is(true));
        PropertyDescriptor prop = props.stream().filter(x -> x.getName().equals("id")).findFirst().get();
        assertThat("comparator", model.getComparator(prop).getComparator().getClass(), is(IgnoreComparatorCustom.class));
    }

    @Test
    public void unit_inspection_inconsistentNaming() throws Throwable {
        AnnotationToComparatorDictionary.getInstance().setComparatorForAnnotation(IgnoreComparatorCustom.class, IgnoreComparisonCustom.class);
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(InconsistentNamingModel.class);
        Set<PropertyDescriptor> props = model.getComparableProperties();
        assertThat("'id' in model", props.stream().anyMatch(x -> x.getName().equals("id")), is(true));
        assertThat("'it' in model", props.stream().anyMatch(x -> x.getName().equals("it")), is(false));
        PropertyDescriptor prop = props.stream().filter(x -> x.getName().equals("id")).findFirst().get();
        assertThat("comparator", model.getComparator(prop).getComparator().getClass(), is(SimpleTypeComparator.class));
    }

    @Test
    public void unit_inspection_GetModelForPrimitiveType() throws Throwable {
        this.expectedException.expect(JEntityModelException.class);
        this.expectedException.expectMessage("Could not get BeanInfo from class: int");

        EntityInspector inspector = new EntityInspector();
        inspector.getComparisonModel(int.class);
    }

    @Test
    public void unit_inspection_fieldWithMultipleStrategies() {
        this.expectedException.expect(JEntityModelException.class);
        this.expectedException.expectMessage("There should be only one Comparison Annotation on your model");
        EntityInspector inspector = new EntityInspector();
        inspector.getComparisonModel(ModelWithMultipleAnnotationsOnMethod.class);
    }
}
