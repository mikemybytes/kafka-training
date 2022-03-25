package com.mikemybytes.kafka.shopping.cart;

import java.util.List;

public class CartOrderedEvent {

    private String id;
    private String user;
    private List<Long> items;

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
        return "CartOrderedEvent{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", items=" + items +
                '}';
    }
}
