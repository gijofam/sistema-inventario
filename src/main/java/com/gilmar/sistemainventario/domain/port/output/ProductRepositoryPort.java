package com.gilmar.sistemainventario.domain.port.output;

import com.gilmar.sistemainventario.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);

    boolean existsByName(String name);
}
