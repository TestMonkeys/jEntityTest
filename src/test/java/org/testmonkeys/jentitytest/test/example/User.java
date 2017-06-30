package org.testmonkeys.jentitytest.test.example;

import org.testmonkeys.jentitytest.framework.ChildEntityComparison;
import org.testmonkeys.jentitytest.framework.ChildEntityListComparison;
import org.testmonkeys.jentitytest.framework.DateTimeComparison;
import org.testmonkeys.jentitytest.framework.IgnoreComparison;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Sample class for showing how jEntityTest comparison annotations can be used
 */
public class User {

    private String firstName;
    private String lastName;
    private int age;
    private boolean married;
    //Marking the list with @ChildEntityListComparison will inspect each list entry
    @ChildEntityListComparison
    private List<User> children;

    @ChildEntityListComparison
    private List<String> emailAddresses;

    @ChildEntityComparison
    private Address address;

    //system properties
    //since we're comparing the time stamp created on server - it might differ from what we marked
    //as created date.
    @DateTimeComparison(delta = 1, unit = ChronoUnit.MINUTES)
    private LocalDateTime createdDate;
    private int id;

    //properties that don't matter will be annotated with @IgnoreComparison
    @IgnoreComparison
    private String randomGenerated;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMarried() {
        return this.married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getChildren() {
        return this.children;
    }

    public void setChildren(List<User> children) {
        this.children = children;
    }

    public String getRandomGenerated() {
        return this.randomGenerated;
    }

    public void setRandomGenerated(String randomGenerated) {
        this.randomGenerated = randomGenerated;
    }

    public List<String> getEmailAddresses() {
        return this.emailAddresses;
    }

    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
