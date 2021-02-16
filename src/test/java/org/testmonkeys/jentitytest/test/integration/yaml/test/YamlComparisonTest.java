package org.testmonkeys.jentitytest.test.integration.yaml.test;

import lombok.SneakyThrows;
import org.junit.Test;
import org.testmonkeys.jentitytest.EntityComparatorContext;
import org.testmonkeys.jentitytest.hamcrest.Entity;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class YamlComparisonTest {

    private Person getExpectedPerson() {
        Person person = new Person();
        person.setAge(21);
        person.setCreatedAt(LocalDateTime.now());
        person.setName("johnny");

        EmploymentRecord employmentRecord = new EmploymentRecord();
        employmentRecord.setEmployer("TestMonkeys");
        employmentRecord.setOnProbation(false);
        person.setEmploymentRecord(employmentRecord);

        Address address1 = new Address();
        address1.setAddressLine1("address line 1");
        address1.setCity("Neverland");

        Address address2 = new Address();
        address2.setCity("New");

        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);
        person.setKnownAddresses(addressList);

        return person;
    }

    @SneakyThrows
    private void initModels() {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yamlComparisonModels/person.yaml");
        EntityComparatorContext.getInstance().defineComparisonModel(inputStream);
        inputStream.close();
        inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yamlComparisonModels/address.yaml");
        EntityComparatorContext.getInstance().defineComparisonModel(inputStream);
        inputStream.close();
    }

    @Test
    public void yamlComplexModelTest() {
        initModels();

        Person expected = getExpectedPerson();
        Person actual = getExpectedPerson();
        actual.setId("id123545111");
        actual.setCreatedAt(LocalDateTime.now().minusSeconds(20));
        actual.setName(expected.getName().toUpperCase());
        actual.getKnownAddresses().get(0).setId("addressId1");

        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void yamlComplexModelTest_failOnPropertiesNotModelled() {
        initModels();

        Person expected = getExpectedPerson();
        Person actual = getExpectedPerson();
        actual.setId("id123545111");
        actual.setCreatedAt(LocalDateTime.now().minusSeconds(20));
        actual.setName(expected.getName().toUpperCase());
        actual.getKnownAddresses().get(0).setId("addressId1");

        //failing:
        actual.setAge(5);

        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void yamlComplexModelTest_failOnChildNotModelled() {
        initModels();

        Person expected = getExpectedPerson();
        Person actual = getExpectedPerson();
        actual.setId("id123545111");
        actual.setCreatedAt(LocalDateTime.now().minusSeconds(20));
        actual.setName(expected.getName().toUpperCase());
        actual.getKnownAddresses().get(0).setId("addressId1");

        //failing:
        actual.getEmploymentRecord().setOnProbation(true);

        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void yamlComplexModelTest_failOnModelledChildInList() {
        initModels();

        Person expected = getExpectedPerson();
        Person actual = getExpectedPerson();
        actual.setId("id123545111");
        actual.setCreatedAt(LocalDateTime.now().minusSeconds(20));
        actual.setName(expected.getName().toUpperCase());
        actual.getKnownAddresses().get(0).setId("addressId1");

        //failing:
        actual.getKnownAddresses().get(0).setCity("GoldenCity");

        assertThat(actual, Entity.isEqualTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void yamlComplexModelTest_failOnValidation() {
        initModels();

        Person expected = getExpectedPerson();
        Person actual = getExpectedPerson();
        actual.setId("id123545111");
        actual.setCreatedAt(LocalDateTime.now().minusSeconds(20));
        actual.setName(expected.getName().toUpperCase());
        actual.getKnownAddresses().get(0).setId("addressId1");

        //failing:
        actual.setName("abcdefghijklmnoprstq");
        expected.setName("abcdefghijklmnoprstq");
        assertThat(actual, Entity.isEqualTo(expected));
    }
}
