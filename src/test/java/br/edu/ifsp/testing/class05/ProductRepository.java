package br.edu.ifsp.testing.class05;

public interface ProductRepository {
    Product findById(String id);
    void update(Product product);
}
