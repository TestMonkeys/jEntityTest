package org.testmonkeys.jentitytest.test.integration.entityInList;


import org.testmonkeys.jentitytest.framework.IgnoreComparison;

public class ItemModel {

    @IgnoreComparison
    private String id;
    private String name;
    private int price;

    public ItemModel(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ItemModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
