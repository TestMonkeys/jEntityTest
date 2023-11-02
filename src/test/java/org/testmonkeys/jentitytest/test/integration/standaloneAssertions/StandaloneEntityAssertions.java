package org.testmonkeys.jentitytest.test.integration.standaloneAssertions;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.testmonkeys.jentitytest.JEntityAssertions.assertEntity;
import static org.testmonkeys.jentitytest.JEntityAssertions.assertEntityCollection;

@Slf4j
public class StandaloneEntityAssertions {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Ignore
    @Test
    public void useCase(){
        User actual = new User();
        actual.setLastName("Snow");
        User expected = new User();
        expected.setLastName("Dow");
        assertEntity("user",actual).isEqualTo(expected);

    }

    @Ignore
    @Test
    public void useCase_inlist(){
        User actual = new User();
        actual.setLastName("Snow");
        User expected = new User();
        expected.setLastName("Dow");
        assertEntity("user",actual).isPresentInCollection(Collections.singleton(new User()));

    }

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
    public void standalone_equals_true(){
        User actual = new User();
        actual.setLastName("Snow");
        User expected = new User();
        expected.setLastName("Snow");
        assertEntity("user",actual).isEqualTo(expected);
    }

    @Test
    public void standalone_equals_false(){
        User actual = new User();
        actual.setLastName("Snow");
        User expected = new User();
        expected.setLastName("Dow");
        AssertionError e=assertThrows(AssertionError.class,()->
        assertEntity(actual).isEqualTo(expected));
        log.info("ERROR message:\n"+ e.getMessage());
        assertEquals("error message",
                "Expected actual entity to be the same as {\"firstName\":null,\"lastName\":\"Dow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                "\t but following properties didnt match:\n" +
                "Property: User.lastName\n" +
                "\tExpected: Dow\n" +
                "\tActual: Snow\n" +
                "Comparison was performed using SimpleTypeComparator\r\n",
                e.getMessage());
    }

    @Test
    public void standalone_named_equals_false(){
        User actual = new User();
        actual.setLastName("Snow");
        User expected = new User();
        expected.setLastName("Dow");
        AssertionError e=assertThrows(AssertionError.class,()->
                assertEntity("user",actual).isEqualTo(expected));
        log.info("ERROR message:\n"+ e.getMessage());
        assertEquals("error message",
                "Expected actual user entity to be the same as {\"firstName\":null,\"lastName\":\"Dow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\t but following properties didnt match:\n" +
                        "Property: User.lastName\n" +
                        "\tExpected: Dow\n" +
                        "\tActual: Snow\n" +
                        "Comparison was performed using SimpleTypeComparator\r\n",
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
        assertEntity("user",actual).isPresentInCollection(list);
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
                assertEntity(actual).isPresentInCollection(list));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Collection has no item like:{\"firstName\":null,\"lastName\":\"Dow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: User.lastName\n" +
                        "\tExpected: Dow\n" +
                        "\tActual: Snow\n" +
                        "Comparison was performed using SimpleTypeComparator\r\n",
                e.getMessage());
    }

    @Test
    public void standalone_named_item_in_list_false(){
        User actual = new User();
        actual.setLastName("Dow");
        User expected = new User();
        expected.setLastName("Snow");
        List<User> list = new ArrayList<>();
        list.add(expected);

        AssertionError e=assertThrows(AssertionError.class,()->
                assertEntity("user",actual).isPresentInCollection(list));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Collection has no user item like:{\"firstName\":null,\"lastName\":\"Dow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: User.lastName\n" +
                        "\tExpected: Dow\n" +
                        "\tActual: Snow\n" +
                        "Comparison was performed using SimpleTypeComparator\r\n",
                e.getMessage());
    }

    @Test
    public void standalone_item_in_empty_list(){
        User actual = new User();
        actual.setLastName("Dow");
        List<User> list = new ArrayList<>();

        AssertionError e=assertThrows(AssertionError.class,()->
                assertEntity("user",actual).isPresentInCollection(list));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Collection has no user item like:{\"firstName\":null,\"lastName\":\"Dow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: Collection\n" +
                        "\tExpected: not empty\n" +
                        "\tActual: empty\n" +
                        "Comparison was performed using null\r\n",
                e.getMessage());
    }

    @Test
    public void standalone_item_in_null_list(){
        User actual = new User();
        actual.setLastName("Dow");


        AssertionError e=assertThrows(AssertionError.class,()->
                assertEntity("user",actual).isPresentInCollection(null));
        log.info("ERROR message:"+ e.getMessage());
        assertEquals("error message",
                "Collection has no user item like:{\"firstName\":null,\"lastName\":\"Dow\",\"age\":0,\"married\":false,\"children\":null,\"emailAddresses\":null,\"address\":null,\"createdDate\":null,\"id\":0,\"randomGenerated\":null}\n" +
                        "\tclosest match has the following differences:\n" +
                        "Property: Collection\n" +
                        "\tExpected: not null\n" +
                        "\tActual: null\n" +
                        "Comparison was performed using null\r\n",
                e.getMessage());
    }
}
