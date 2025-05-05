package br.edu.ifsp.testing.class06.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private final List<OrderItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public List<OrderItem> items() {
        return List.copyOf(items);
    }

    public enum Status {PENDING}
    public Status status;

    public void add(OrderItem item) {
        items.add(item);
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
