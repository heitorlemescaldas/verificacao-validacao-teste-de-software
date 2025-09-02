package br.edu.ifsp.testing.class05;

import java.util.Objects;

public class UpdateProduct {
    private final ProductRepository repository;

    public UpdateProduct(ProductRepository repository) {
        this.repository = repository;
    }

    public boolean update(String id, String newName, double newPrice, int newQuantity) {
        Objects.requireNonNull(id);

        Product existing = repository.findById(id);
        if (existing == null) {
            return false; // produto não existe
        }

        if (newPrice < 0 || newQuantity < 0 || newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Invalid data");
        }

        existing.setName(newName);
        existing.setPrice(newPrice);
        existing.setQuantity(newQuantity);

        repository.update(existing);
        return true;
    }
}
