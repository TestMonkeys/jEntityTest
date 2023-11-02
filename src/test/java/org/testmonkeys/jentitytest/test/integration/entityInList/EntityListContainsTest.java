package org.testmonkeys.jentitytest.test.integration.entityInList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testmonkeys.jentitytest.exceptions.JEntityTestException;
import org.testmonkeys.jentitytest.hamcrest.Entity;
import org.testmonkeys.jentitytest.hamcrest.EntityList;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class EntityListContainsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void entityInList_ListNotIterable() {
        expectedException.expect(JEntityTestException.class);
        expectedException.expectMessage("Actual object not iterable");
        ItemModel model = new ItemModel();
        assertThat(model, EntityList.contains(model));
    }

    @Test
    public void entityInList_emptyList() {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage(
                "List contains entity {\"id\":null,\"name\":null,\"price\":0}\n" +
                        "     but: Actual list is empty");
        ItemModel model = new ItemModel();
        assertThat(new ArrayList<ItemModel>(), EntityList.contains(model));
    }

    @Test
    public void entityInList_match_in_List() {
        ItemModel model = new ItemModel("id1", "modelName", 15);
        List<ItemModel> modelList = new ArrayList<>();
        modelList.add(new ItemModel(null, "modelName", 15));
        assertThat(modelList, EntityList.contains(model));
    }

    @Test
    public void entityInList_match_not_in_List() {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("List contains entity {\"id\":\"id1\",\"name\":\"modelName\",\"price\":15}\n" +
                "     but: Entity not in list, closest match has the following differences:\n" +
                "Property: ItemModel.name\n" +
                "\tExpected: modelName\n" +
                "\tActual: modelName2");
        ItemModel model = new ItemModel("id1", "modelName", 15);
        List<ItemModel> modelList = new ArrayList<>();
        modelList.add(new ItemModel(null, "modelName1", 12));
        modelList.add(new ItemModel(null, "modelName2", 15));
        modelList.add(new ItemModel(null, "modelName3", 13));
        assertThat(modelList, EntityList.contains(model));
    }

    @Test
    public void entityInList_match_not_in_List_expected_null() {
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage("List contains entity null\n" +
                "     but: Entity not in list, closest match has the following differences:\n" +
                "Property: ItemModel\n" +
                "\tExpected: null\n" +
                "\tActual: not null");
        ItemModel model = null;
        List<ItemModel> modelList = new ArrayList<>();
        modelList.add(new ItemModel(null, "modelName1", 12));
        modelList.add(new ItemModel(null, "modelName2", 15));
        modelList.add(new ItemModel(null, "modelName3", 13));
        assertThat(modelList, EntityList.contains(model));
    }

    @Test
    public void entityInList_match__in_List_expected_null() {
        ItemModel model = null;
        List<ItemModel> modelList = new ArrayList<>();
        modelList.add(new ItemModel(null, "modelName1", 12));
        modelList.add(new ItemModel(null, "modelName2", 15));
        modelList.add(null);
        assertThat(modelList, EntityList.contains(model));
    }
}
