package com.gilmar.sistemainventario.infrastructure.entity;



// import com.gilmar.sistemainventario.domain.model.ProductCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // @Enumerated(EnumType.STRING)
    // @Column(nullable = false)
    // private ProductCategory category;

  


    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int currentStock;

    @Column(nullable = false)
    private int minimumStock;

     // RELACIÓN: Muchos productos pertenecen a una Categoría
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryJpaEntity category;

    // RELACIÓN: Muchos productos pertenecen a un Proveedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierJpaEntity supplier;

    public ProductJpaEntity() {
    }

    public ProductJpaEntity(Long id, String name, CategoryJpaEntity category, SupplierJpaEntity supplier, String description, int currentStock, int minimumStock) {
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

    public CategoryJpaEntity getCategory() {
        return category;
    }

    public SupplierJpaEntity getSupplier() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(CategoryJpaEntity category) {
        this.category = category;
    }

    public void setSupplier(SupplierJpaEntity supplier) {
        this.supplier = supplier;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }
}
