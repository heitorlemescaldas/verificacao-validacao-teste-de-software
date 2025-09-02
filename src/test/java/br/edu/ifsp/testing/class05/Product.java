package br.edu.ifsp.testing.class05;

import java.util.Objects;

public class Product {
    private final String id;
    private String name;
    private double price;
    private int quantity;

    public Product(String id, String name, double price, int quantity) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        if (price < 0) throw new IllegalArgumentException("Price must be non-negative");
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be non-negative");

        this.price = price;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setName(String name) { this.name = Objects.requireNonNull(name); }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price must be non-negative");
        this.price = price;
    }
    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be non-negative");
        this.quantity = quantity;
    }
}
