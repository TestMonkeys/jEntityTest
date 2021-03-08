package org.testmonkeys.jentitytest.test.unit.hamcrest;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DescriptionTest {


    @Test
    public void describeTo() {
        Model model = new Model();
        model.setAge(1);
        model.setName("Gugu");
        Matcher matcher = Entity.isEqualTo(model);
        assertThat(matcher.toString(), is("Actual entity matches {\"name\":\"Gugu\",\"age\":1}"));
    }

    public class Model {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
