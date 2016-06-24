package entityInspectionTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import org.testmonkeys.jentitytest.comparison.ComparisonModel;
import org.testmonkeys.jentitytest.comparison.property.IgnoreComparator;
import org.testmonkeys.jentitytest.inspect.EntityInspector;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import testModels.inspection.InvalidProperties;
import testModels.inspection.Model1;

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
        PropertyDescriptor prop=props.stream().findFirst().filter(x -> x.getName().equals("id")).get();
        assertThat("comparator",model.getComparator(prop).getClass(), is(IgnoreComparator.class));
    }

    @Test
    public void inspectionIsGetterLevelAnnotation() throws Throwable{
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(Model1.class);
        Set<PropertyDescriptor> props= model.getComparableProperties();
        assertThat("'available' in model",props.stream().anyMatch(x -> x.getName().equals("available")),is(true));
        PropertyDescriptor prop=props.stream().findFirst().filter(x -> x.getName().equals("available")).get();
        assertThat("comparator",model.getComparator(prop).getClass(), is(IgnoreComparator.class));
    }

    @Test
    public void inspectionSimpleGetterLevelAnnotation() throws Throwable{
        EntityInspector inspector = new EntityInspector();
        ComparisonModel model = inspector.getComparisonModel(Model1.class);
        Set<PropertyDescriptor> props= model.getComparableProperties();
        assertThat("'age' in model",props.stream().anyMatch(x -> x.getName().equals("age")),is(true));
        PropertyDescriptor prop=props.stream().findFirst().filter(x -> x.getName().equals("age")).get();
        assertThat("comparator",model.getComparator(prop).getClass(), is(IgnoreComparator.class));
    }

    @Test
    public void inspectionTakesFiledAnnotationInsteadOfGetter(){
        throw new NotImplementedException();
    }
}
