package com.mikemybytes.kafka.shopping.cart;

import java.util.List;

public class Cart {

    private String id;
    private String user;
    private List<Long> items;

    public Cart() {
        // empty
    }

    public Cart(String id, String user, List<Long> items) {
        this();

        this.id = id;
        this.user = user;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", items=" + items +
                '}';
    }

}
