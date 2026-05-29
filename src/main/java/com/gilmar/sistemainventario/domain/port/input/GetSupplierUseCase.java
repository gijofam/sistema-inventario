package com.gilmar.sistemainventario.domain.port.input;

import java.util.List;

import com.gilmar.sistemainventario.domain.model.Supplier;

public interface GetSupplierUseCase {
    Supplier getById(Long id);

    List<Supplier> listAll();
}
