package org.testmonkeys.jentitytest.test.example;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpascal on 6/30/2017.
 */
public class UsageExample {


    @Test
    @Ignore
    public void userExample_EntityTest() {
        User expected = getExpectedUser();
        User actual = getActualUser();
        Assert.assertThat(actual, Entity.isEqualTo(expected));

    }

    private User getActualUser() {
        User user = new User();
        user.setAge(22);
        user.setFirstName("John");
        user.setLastName("Does");
        user.setCreatedDate(LocalDateTime.now());
        user.setId(1);

        //Children
        User child1 = new User();
        child1.setFirstName("Jeremy");
        child1.setLastName("Doe");

        List<User> childrenList = new ArrayList<>();
        //childrenList.add(child1);
        user.setChildren(childrenList);

        //emails
        List<String> emails = new ArrayList<>();
//        emails.add("john@flimsy.com");
//        emails.add("flimsyj@company.com");
        emails.add("email1");
        emails.add("email2");
        user.setEmailAddresses(emails);

        return user;
    }

    private User getExpectedUser() {
        User user = new User();
        user.setAge(22);
        user.setFirstName("John");
        user.setLastName("Flimsy");
        user.setCreatedDate(LocalDateTime.now().plus(5, ChronoUnit.SECONDS));
        user.setId(1);

        //Children
        User child1 = new User();
        child1.setFirstName("Jeremy");
        child1.setLastName("Flimsy");

        List<User> childrenList = new ArrayList<>();
        childrenList.add(child1);
        user.setChildren(childrenList);

        //emails
        List<String> emails = new ArrayList<>();
        emails.add("john@flimsy.com");
        emails.add("flimsyj@company.com");
        user.setEmailAddresses(emails);

        return user;
    }
}
