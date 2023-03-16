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
import org.testmonkeys.jentitytest.hamcrest.EntityList;
import org.testmonkeys.jentitytest.hamcrest.matchers.DefaultResultOutput;
import org.testmonkeys.jentitytest.hamcrest.matchers.EntityListMatcher;
import org.testmonkeys.jentitytest.hamcrest.matchers.EntityMatcher;
import org.testmonkeys.jentitytest.hamcrest.matchers.ResultProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpascal on 6/21/2017.
 */
public class EntityListFacadeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @After
    public void cleanupAfter() {
        EntityList.setResultProcessor(new DefaultResultOutput());
    }

    @Test
    public void entityListFacade_classConstructor() {
        EntityList en = new EntityList();
    }

    @Test
    public void entityListFacade_setNullResultProcessor() {
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("resultProcessor can not be null");
        EntityList.setResultProcessor(null);
    }

    @Test
    public void entityListFacade_setCustomResultProcessor() {
        EntityList.setResultProcessor(new CustomResultProcessor());
        List<EntityListFacadeTest.User> expectedList=new ArrayList<>();
        expectedList.add(new EntityListFacadeTest.User("two"));
        EntityListMatcher<List<EntityListFacadeTest.User>> matcher = EntityList.isEqualTo(expectedList);

        List<EntityListFacadeTest.User> actualList=new ArrayList<>();
        actualList.add(new EntityListFacadeTest.User("one"));
        matcher.matches(actualList);
        EntityListFacadeTest.CustomDescription description = new EntityListFacadeTest.CustomDescription();
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
    }

}
