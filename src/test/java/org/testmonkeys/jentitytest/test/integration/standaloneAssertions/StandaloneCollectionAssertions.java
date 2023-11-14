package org.testmonkeys.jentitytest.test.integration.standaloneAssertions;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.testmonkeys.jentitytest.JEntityAssertions.assertEntityCollection;

@Slf4j
public class StandaloneCollectionAssertions {

    @Ignore
    @Test
    public void useCase_inlist_multiple(){
        User actual = new User();
        actual.setLastName("Snow");
        User expected = new User();
        expected.setLastName("Dow");
        List<User> users= new ArrayList<>();
        users.add(actual);
        users.add(expected);
        List<User> actualList =new ArrayList<>();
        actualList.add(new User());
        assertEntityCollection("user list",actualList).hasItems(users);

    }

    @Test
    public void standalone_list_equals_true(){
        User actual = new User();
        actual.setLastName("Snow");
        List<User> actualList=new ArrayList<>();
        actualList.add(actual);
        User expected = new User();
        expected.setLastName("Snow");
        List<User> expectedList=new ArrayList<>();
        expectedList.add(expected);
        assertEntityCollection("user",actualList).isEqualTo(expectedList);
    }

    @Test
    public void standalone_list_equals_true_ignoreOrder(){
        User actual = new User();
        actual.setLastName("Snow");
        User actual2 = new User();
        actual2.setLastName("Bow");
        List<User> actualList=new ArrayList<>();
        actualList.add(actual);
        actualList.add(actual2);
        User expected = new User();
        expected.setLastName("Bow");
        User expected2 = new User();
        expected2.setLastName("Snow");
        List<User> expectedList=new ArrayList<>();
        expectedList.add(expected);
        expectedList.add(expected2);
        assertEntityCollection("user",actualList).ignoringOrder().isEqualTo(expectedList);
    }

    @Test
    public void standalone_list_equals_false_ignoreOrder(){
        User actual = new User();
        actual.setLastName("Snow");
        User actual2 = new User();
        actual2.setLastName("Jow");
        List<User> actualList=new ArrayList<>();
        actualList.add(actual);
        actualList.add(actual2);
        User expected = new User();
        expected.setLastName("Snow");
        User expected2 = new User();
        expected2.setLastName("Dow");
        List<User> expectedList=new ArrayList<>();
        expectedList.add(expected);
        expectedList.add(expected2);
        AssertionError e=assertThrows(AssertionError.class,()->
        assertEntityCollection("user",actualList).ignoringOrder().isEqualTo(expectedList));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Expected actual user entity collection to be the same as [{\"firstName\":null,\"lastName\":\"Snow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null},{\"firstName\":null,\"lastName\":\"Dow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}]\n" +
                        "\t but following properties didnt match:\n" +
                        "Property: List.lastName\n" +
                        "\tExpected: Dow\n" +
                        "\tActual: Jow\r\n",
                e.getMessage());
    }


    @Test
    public void standalone_list_equals_false(){
        User actual = new User();
        actual.setLastName("Snow");
        List<User> actualList=new ArrayList<>();
        actualList.add(actual);
        User expected = new User();
        expected.setLastName("Dow");
        List<User> expectedList=new ArrayList<>();
        expectedList.add(expected);
        AssertionError e=assertThrows(AssertionError.class,()->
        assertEntityCollection(actualList).isEqualTo(expectedList));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Expected actual entity collection to be the same as [{\"firstName\":null,\"lastName\":\"Dow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}]\n" +
                        "\t but following properties didnt match:\n" +
                        "Property: List[0].lastName\n" +
                        "\tExpected: Dow\n" +
                        "\tActual: Snow\r\n",
                e.getMessage());
    }


    @Test
    public void standalone_item_in_list(){
        User actual = new User();
        actual.setLastName("Snow");
        User expected = new User();
        expected.setLastName("Snow");
        List<User> list = new ArrayList<>();
        list.add(expected);
        assertEntityCollection("user list",list).hasItem(actual);
    }

    @Test
    public void standalone_item_in_list_false(){
        User actual = new User();
        actual.setLastName("Dow");
        User expected = new User();
        expected.setLastName("Snow");
        List<User> list = new ArrayList<>();
        list.add(expected);

        AssertionError e=assertThrows(AssertionError.class,()->
                assertEntityCollection("user",list).hasItem(actual));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Collection has no user item like:{\"firstName\":null,\"lastName\":\"Dow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: User.lastName\n" +
                        "\tExpected: Dow\n" +
                        "\tActual: Snow\r\n",
                e.getMessage());
    }

    @Test
    public void standalone_items_in_list(){
        List<User> list = new ArrayList<>();
        list.add(new User("Snow"));
        list.add(new User("Bow"));
        list.add(new User("Dow"));

        List<User> expectedItems = new ArrayList<>();
        expectedItems.add(new User("Snow"));
        expectedItems.add(new User("Dow"));
        assertEntityCollection("user list",list).hasItems(expectedItems);
    }

    @Test
    public void standalone_items_in_list_false(){
        List<User> list = new ArrayList<>();
        list.add(new User("Snow"));
        list.add(new User("Bow"));
        list.add(new User("Dow"));
        list.add(new User("Row"));

        List<User> expectedItems = new ArrayList<>();
        expectedItems.add(new User("Snow"));
        expectedItems.add(new User("Mow"));
        expectedItems.add(new User("Cow"));
        AssertionError e=assertThrows(AssertionError.class,()->
        assertEntityCollection(list).hasItems(expectedItems));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Collection has no item like:{\"firstName\":null,\"lastName\":\"Mow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: User.lastName\n" +
                        "\tExpected: Mow\n" +
                        "\tActual: Snow\r\n" +
                        "Collection has no item like:{\"firstName\":null,\"lastName\":\"Cow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: User.lastName\n" +
                        "\tExpected: Cow\n" +
                        "\tActual: Snow\r\n",
                e.getMessage());
    }

    @Test
    public void standalone_named_items_in_list_false(){
        List<User> list = new ArrayList<>();
        list.add(new User("Snow"));
        list.add(new User("Bow"));
        list.add(new User("Dow"));
        list.add(new User("Row"));

        List<User> expectedItems = new ArrayList<>();
        expectedItems.add(new User("Snow"));
        expectedItems.add(new User("Mow"));
        expectedItems.add(new User("Cow"));
        AssertionError e=assertThrows(AssertionError.class,()->
                assertEntityCollection("user list",list).hasItems(expectedItems));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Collection has no user list item like:{\"firstName\":null,\"lastName\":\"Mow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: User.lastName\n" +
                        "\tExpected: Mow\n" +
                        "\tActual: Snow\r\n" +
                        "Collection has no user list item like:{\"firstName\":null,\"lastName\":\"Cow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: User.lastName\n" +
                        "\tExpected: Cow\n" +
                        "\tActual: Snow\r\n",
                e.getMessage());
    }

    @Test
    public void standalone_named_items_in_empty_list(){
        List<User> list = new ArrayList<>();


        List<User> expectedItems = new ArrayList<>();
        expectedItems.add(new User("Snow"));
        expectedItems.add(new User("Mow"));
        expectedItems.add(new User("Cow"));
        AssertionError e=assertThrows(AssertionError.class,()->
                assertEntityCollection("user list",list).hasItems(expectedItems));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Collection has no user list item like:{\"firstName\":null,\"lastName\":\"Snow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: Collection\n" +
                        "\tExpected: not empty\n" +
                        "\tActual: empty\r\n" +
                        "Collection has no user list item like:{\"firstName\":null,\"lastName\":\"Mow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: Collection\n" +
                        "\tExpected: not empty\n" +
                        "\tActual: empty\r\n" +
                        "Collection has no user list item like:{\"firstName\":null,\"lastName\":\"Cow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: Collection\n" +
                        "\tExpected: not empty\n" +
                        "\tActual: empty\r\n",
                e.getMessage());
    }

    @Test
    public void standalone_named_items_in_null_list(){
        List<User> list = null;


        List<User> expectedItems = new ArrayList<>();
        expectedItems.add(new User("Snow"));
        expectedItems.add(new User("Mow"));
        expectedItems.add(new User("Cow"));

        AssertionError e=assertThrows(AssertionError.class,()->
                assertEntityCollection("user list",list).hasItems(expectedItems));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Collection has no user list item like:{\"firstName\":null,\"lastName\":\"Snow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: Collection\n" +
                        "\tExpected: not null\n" +
                        "\tActual: null\r\n" +
                        "Collection has no user list item like:{\"firstName\":null,\"lastName\":\"Mow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: Collection\n" +
                        "\tExpected: not null\n" +
                        "\tActual: null\r\n" +
                        "Collection has no user list item like:{\"firstName\":null,\"lastName\":\"Cow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: Collection\n" +
                        "\tExpected: not null\n" +
                        "\tActual: null\r\n",
                e.getMessage());
    }
}
