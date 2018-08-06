# jEntityTest
[![CircleCI](https://circleci.com/gh/TestMonkeys/jEntityTest/tree/develop.svg?style=svg)](https://circleci.com/gh/TestMonkeys/jEntityTest)
[![codecov](https://codecov.io/gh/TestMonkeys/jEntityTest/branch/master/graph/badge.svg)](https://codecov.io/gh/TestMonkeys/jEntityTest)

jEntityTest is a library that can compare two instances of a class based on their declared fields. Making it easier to check if they are equal. It also contains a hamcrest matcher so that you can use the comparison in assertions. The comparison can further be customized via annotations for each field.

# Usage Example

A simple use case would be creating a user via API, then retrieving it and checking it was created correctly:
```java
User createdUser = api.createUser(newUser);
assertThat(createdUser, Entity.isEqualTo(newUser));
```
In the above case, `Entity.isEqualTo`, which is part of jEntityTest, will do the comparison between the two objects. 
Now let's look at the actual fields that the User object has

User fields  | newUser values | createdUser values 
------------ | -------------- | ------------------ 
id           |                | 1
firstName    | John           | John
lastName     | Doe            | Doe

The id was generated on while creating the user, so we can't know it beforehand. For the comparison to not fail in this case - fields can be decorated with annotations specifying the comparison strategy to be applied, so the user model would be:
```java

public class User{

  @IgnoreComparison
  private String id;
  
  private String firstName;
  
  private String lastName;
  ...

```
There are a few more comparison strategies that can be applied for any field, that will impact how the comparison is done.

# Comparison Strategies available
For a more detailed explanation of each comparison strategy please visit the [wiki](https://github.com/TestMonkeys/jEntityTest/wiki/JEntityTest-comparison-strategies) 


## ChildEntityComparison

`@ChildEntityComparison` can be applied to a field if it is an object that you want to also compare by it's fields. 

## ChildEntityListComparison

`@ChildEntityListComparison` can be applied to lists, this will compare each item in the list between the expected and actual objects.

## DateTimeComparison

`@DateTimeComparison` can be applied to LocalDateTime fields - and is parameterized to allow for a delta. 

## StringComparison

`@StringComparison` can be applied to String fields and can be parameterized to trim, ignore specific characters, or ignore the case.
