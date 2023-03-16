package org.testmonkeys.jentitytest.test.integration.listComparison;

import org.hamcrest.core.StringContains;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.hamcrest.EntityList;
import org.testmonkeys.jentitytest.test.integration.entityInList.ItemModel;
import org.testmonkeys.jentitytest.test.integration.models.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class EntityListComparison {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void listComparison_null_null(){
        assertThat("null lists", null, EntityList.isEqualTo(null));
    }

    @Test
    public void listComparison_null_notnull(){
        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Property: List\n" +
                "\tExpected: not null ([])\n" +
                "\tActual: null"));
        assertThat("null lists", null, EntityList.isEqualTo(new ArrayList<User>()));
    }

    @Test
    public void listComparison_notnull_null(){
        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Property: List\n" +
                "\tExpected: null\n" +
                "\tActual: not null ([])"));
        assertThat("null lists", new ArrayList<User>(), EntityList.isEqualTo(null));
    }

    @Test
    public void listComparison_empty_empty(){
        assertThat("empty lists", new ArrayList<User>(), EntityList.isEqualTo(new ArrayList<User>()));
    }

    @Test
    public void listComparison_empty_notEmpty(){
        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Property: List.Size\n" +
                "\tExpected: 1\n" +
                "\tActual: 0"));
        ArrayList<User> notEmptyList=new ArrayList<>();
        notEmptyList.add(new User());
        assertThat("count lists", new ArrayList<User>(), EntityList.isEqualTo(notEmptyList));
    }

    @Test
    public void listComparison_notEmpty_empty(){
        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Property: List.Size\n" +
                "\tExpected: 0\n" +
                "\tActual: 1"));
        ArrayList<User> notEmptyList=new ArrayList<>();
        notEmptyList.add(new User());
        assertThat("count lists",notEmptyList , EntityList.isEqualTo(new ArrayList<User>()));
    }

    @Test
    public void listComparison_equivalent_lists_withObjects(){
        List<ItemModel> expected = new ArrayList<>();
        expected.add(new ItemModel(null, "modelName1", 12));
        expected.add(new ItemModel(null, "modelName2", 15));
        expected.add(new ItemModel(null, "modelName3", 13));
        List<ItemModel> actual = new ArrayList<>();
        actual.add(new ItemModel(null, "modelName1", 12));
        actual.add(new ItemModel(null, "modelName2", 15));
        actual.add(new ItemModel(null, "modelName3", 13));


        assertThat("count lists",expected , EntityList.isEqualTo(actual));
    }

    @Test
    public void listComparison_equivalent_lists_withObjects_diff(){
        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Property: List[0].price\n" +
                "\tExpected: 12\n" +
                "\tActual: 13"));

        List<ItemModel> expected = new ArrayList<>();
        expected.add(new ItemModel(null, "modelName1", 13));
        expected.add(new ItemModel(null, "modelName2", 15));
        expected.add(new ItemModel(null, "modelName3", 13));
        List<ItemModel> actual = new ArrayList<>();
        actual.add(new ItemModel("1", "modelName1", 12));
        actual.add(new ItemModel("2", "modelName2", 15));
        actual.add(new ItemModel(null, "modelName3", 13));


        assertThat("count lists",expected , EntityList.isEqualTo(actual));
    }

    @Test
    public void listComparison_equivalent_lists_withPrimitives(){
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        expected.add(4);
        List<Integer> actual = new ArrayList<>();
        actual.add(2);
        actual.add(3);
        actual.add(4);


        assertThat("count lists",expected , EntityList.isEqualTo(actual));
    }

    @Test
    public void listComparison_equivalent_lists_withPrimitives_diff(){
        expectedEx.expect(AssertionError.class);
        expectedEx.expectMessage(StringContains.containsString("Property: List[0]\n" +
                "\tExpected: 2\n" +
                "\tActual: 1"));

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(3);
        expected.add(4);
        List<Integer> actual = new ArrayList<>();
        actual.add(2);
        actual.add(3);
        actual.add(4);


        assertThat("count lists",expected , EntityList.isEqualTo(actual));
    }
}
