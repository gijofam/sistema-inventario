package com.gilmar.sistemainventario.domain.port.output;

import com.gilmar.sistemainventario.domain.model.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierRepositoryPort {

    Supplier save(Supplier supplier);

    Optional<Supplier> findById(Long id);

    List<Supplier> findAll();

    void deleteById(Long id);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

}    