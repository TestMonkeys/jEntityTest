package org.testmonkeys.jentitytest.test.unit.model;


import org.junit.Before;
import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.strategies.IgnoreComparator;
import org.testmonkeys.jentitytest.model.ComparisonModel;
import org.testmonkeys.jentitytest.model.EntityInspector;
import org.testmonkeys.jentitytest.test.unit.model.util.ModelMultiAnnotated;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * This class represents tests for inspecting models that have annotations from multiple frameworks
 */
public class InspectionTestMultiFrameworkAnnotations {

    private ComparisonModel model;
    private Set<PropertyDescriptor> props;

    /**
     * initialization of EntityInspector,
     * generation of comparison model for ModelMultiAnnotated.class,
     * retrieval of comparable properties
     *
     * @throws IntrospectionException
     */
    @Before
    public void background() throws IntrospectionException {
        EntityInspector inspector = new EntityInspector();
        this.model = inspector.getComparisonModel(ModelMultiAnnotated.class);
        this.props = this.model.getComparableProperties();
    }

    /**
     * 'Id' property descriptor is annotated at field level with
     *
     * @throws Throwable
     * @SimpleAnnotation and @IgnoreComparison,
     * jEntityTest should ignore SimpleAnnotation, and should use only IgnoreComparator
     */
    @Test
    public void inspectionFieldLevelAnnotation() throws Throwable {


        assertThat("'id' in model", this.props.stream().anyMatch(x -> x.getName().equals("id")), is(true));
        PropertyDescriptor prop = this.props.stream().filter(x -> x.getName().equals("id")).findFirst().get();
        assertThat("comparator", this.model.getComparator(prop).getComparator().getClass(), is(IgnoreComparator.class));
    }


    /**
     * 'Available' property descriptor is annotated at field level with
     *
     * @throws Throwable
     * @SimpleAnnotation and at getter level with @IgnoreComparison,
     * jEntityTest should ignore SimpleAnnotation, and should use only IgnoreComparator
     */
    @Test
    public void inspectionIsGetterLevelAnnotation() throws Throwable {
        assertThat("'available' in model", this.props.stream().anyMatch(x -> x.getName().equals("available")), is(true));
        PropertyDescriptor prop = this.props.stream().filter(x -> x.getName().equals("available")).findFirst().get();
        assertThat("comparator", this.model.getComparator(prop).getComparator().getClass(), is(IgnoreComparator.class));
    }


    /**
     * 'Age' property descriptor is annotated at getter level with
     *
     * @throws Throwable
     * @SimpleAnnotation and @IgnoreComparison,
     * jEntityTest should ignore SimpleAnnotation, and should use only IgnoreComparator
     */
    @Test
    public void inspectionSimpleGetterLevelAnnotation() throws Throwable {
        assertThat("'age' in model", this.props.stream().anyMatch(x -> x.getName().equals("age")), is(true));
        PropertyDescriptor prop = this.props.stream().filter(x -> x.getName().equals("age")).findFirst().get();
        assertThat("comparator", this.model.getComparator(prop).getComparator().getClass(), is(IgnoreComparator.class));
    }

}
