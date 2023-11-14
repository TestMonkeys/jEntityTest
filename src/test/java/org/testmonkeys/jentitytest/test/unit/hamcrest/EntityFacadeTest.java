package org.testmonkeys.jentitytest.test.unit.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.core.StringContains;
import org.hamcrest.junit.ExpectedException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.testmonkeys.jentitytest.comparison.ComparisonContext;
import org.testmonkeys.jentitytest.comparison.result.ComparisonResult;
import org.testmonkeys.jentitytest.hamcrest.Entity;
import org.testmonkeys.jentitytest.hamcrest.matchers.DefaultResultOutput;
import org.testmonkeys.jentitytest.hamcrest.matchers.EntityMatcher;
import org.testmonkeys.jentitytest.hamcrest.matchers.ResultProcessor;

/**
 * Created by cpascal on 6/21/2017.
 */
public class EntityFacadeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @After
    public void cleanupAfter() {
        Entity.setResultProcessor(new DefaultResultOutput());
    }

    @Test
    public void entityFacade_classConstructor() {
        Entity en = new Entity();
    }

    @Test
    public void entityFacade_setNullResultProcessor() {
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("resultProcessor can not be null");
        Entity.setResultProcessor(null);
    }

    @Test
    public void entityFacade_setCustomResultProcessor() {
        Entity.setResultProcessor(new CustomResultProcessor());
        EntityMatcher<EntityFacadeTest.User> matcher = Entity.isEqualTo(new EntityFacadeTest.User("two"));
        matcher.matches(new EntityFacadeTest.User("one"));
        EntityFacadeTest.CustomDescription description = new EntityFacadeTest.CustomDescription();
        matcher.describeMismatch(null, description);

        Assert.assertThat(description.getText(), StringContains.containsString("CustomResultOutput"));
    }

    public class CustomDescription implements Description {

        String text;

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public Description appendText(String text) {
            this.text += text;
            return this;
        }

        @Override
        public Description appendDescriptionOf(SelfDescribing value) {
            return null;
        }

        @Override
        public Description appendValue(Object value) {
            return null;
        }

        @Override
        public <T> Description appendValueList(String start, String separator, String end, T[] values) {
            return null;
        }

        @Override
        public <T> Description appendValueList(String start, String separator, String end, Iterable<T> values) {
            return null;
        }

        @Override
        public Description appendList(String start, String separator, String end, Iterable<? extends SelfDescribing> values) {
            return null;
        }
    }

    public class User {
        private String name;

        User(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class CustomResultProcessor implements ResultProcessor {

        @Override
        public String getOutput(ComparisonContext context, ComparisonResult result) {
            return "CustomResultOutput";
        }

        @Override
        public String describeObject(Object o) {
            return o.toString();
        }
    }

}
