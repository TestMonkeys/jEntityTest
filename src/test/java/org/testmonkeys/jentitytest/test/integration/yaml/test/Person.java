package org.testmonkeys.jentitytest.test.integration.yaml.test;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Person {

    private String id;
    private String name;
    private int age;
    private LocalDateTime createdAt;
    private EmploymentRecord employmentRecord;
    private List<Address> knownAddresses;
}
