package com.gilmar.sistemainventario.domain.model;

public class Product {

    private Long id;
    private String name;
    private Category category;
    private Supplier supplier;
    private String description;
    private int currentStock;
    private int minimumStock;

    public Product(Long id, String name, Category category, Supplier supplier, String description, int currentStock, int minimumStock) {
        validateName(name);
        validateStock(currentStock);
        validateMinimumStock(minimumStock);
        this.id = id;
        this.name = name;
        this.category = category;
        this.supplier = supplier;
        this.description = description;
        this.currentStock = currentStock;
        this.minimumStock = minimumStock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public String getDescription() {
        return description;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void updateDetails(String name, Category category, Supplier supplier, String description, int minimumStock) {
        validateName(name);
        validateMinimumStock(minimumStock);
        this.name = name;
        this.category = category;
        this.supplier = supplier;
        this.description = description;
        this.minimumStock = minimumStock;
    }

    public void adjustStock(int delta) {
        int updatedStock = this.currentStock + delta;
        validateStock(updatedStock);
        this.currentStock = updatedStock;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
    }

    private void validateStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
    }

    private void validateMinimumStock(int minimumStock) {
        if (minimumStock < 0) {
            throw new IllegalArgumentException("El stock mínimo no puede ser negativo.");
        }
    }
}
