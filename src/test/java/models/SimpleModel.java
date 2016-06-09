package models;

/**
 * Created by cpascal on 6/7/2016.
 */
public class SimpleModel {

    public SimpleModel(String name, int age){
        this.name=name;
        this.age=age;
    }

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
