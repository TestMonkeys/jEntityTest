package org.testmonkeys.jentitytest.test.unit.entityInspectionTests;

import static org.junit.Assert.*;

import org.testmonkeys.jentitytest.test.unit.entityInspectionTests.models.*;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.property.IgnoreComparator;
import org.testmonkeys.jentitytest.inspect.EntityInspector;
import org.testmonkeys.jentitytest.inspect.ModelToComparisonMap;

import java.beans.PropertyDescriptor;
import java.util.Set;

public class InspectionTest {

    @Test
    public void inspectionNoHasGetters() throws Throwable{
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(InvalidProperties.class);
        Set<PropertyDescriptor> props= model.getComparableProperties();
        assertThat("hasProp in model",props.stream().anyMatch(x -> x.getReadMethod().equals("hasMarshmallows")),is(false));
    }

    @Test
    public void inspectionFieldLevelAnnotation() throws Throwable{
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(Model1.class);
        Set<PropertyDescriptor> props= model.getComparableProperties();
        assertThat("'id' in model",props.stream().anyMatch(x -> x.getName().equals("id")),is(true));
        PropertyDescriptor prop = props.stream().filter(x -> x.getName().equals("id")).findFirst().get();
        assertThat("comparator",model.getComparator(prop).getClass(), is(IgnoreComparator.class));
    }

    @Test
    public void inspectionIsGetterLevelAnnotation() throws Throwable{
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(Model1.class);
        Set<PropertyDescriptor> props= model.getComparableProperties();
        assertThat("'available' in model",props.stream().anyMatch(x -> x.getName().equals("available")),is(true));
        PropertyDescriptor prop = props.stream().filter(x -> x.getName().equals("available")).findFirst().get();
        assertThat("comparator",model.getComparator(prop).getClass(), is(IgnoreComparator.class));
    }

    @Test
    public void inspectionSimpleGetterLevelAnnotation() throws Throwable{
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(Model1.class);
        Set<PropertyDescriptor> props= model.getComparableProperties();
        assertThat("'age' in model",props.stream().anyMatch(x -> x.getName().equals("age")),is(true));
        PropertyDescriptor prop = props.stream().filter(x -> x.getName().equals("age")).findFirst().get();
        assertThat("comparator",model.getComparator(prop).getClass(), is(IgnoreComparator.class));
    }

    @Test
    public void inspectionTakesFiledAnnotationInsteadOfGetter() throws Throwable {
        ModelToComparisonMap.getInstance().setComparatorForAnnotation(IgnoreComparatorCustom.class, IgnoreComparisonCustom.class);
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(ModelWithBothLevelAnnotations.class);
        Set<PropertyDescriptor> props = model.getComparableProperties();
        assertThat("'id' in model", props.stream().anyMatch(x -> x.getName().equals("id")), is(true));
        PropertyDescriptor prop = props.stream().filter(x -> x.getName().equals("id")).findFirst().get();
        assertThat("comparator", model.getComparator(prop).getClass(), is(IgnoreComparatorCustom.class));
    }
}
